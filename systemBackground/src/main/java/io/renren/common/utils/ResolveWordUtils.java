package io.renren.common.utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolveWordUtils {

    public static List<Map<String, Object>> getData(MultipartFile file) {
        String absolutePath = "/home/lancemao/Documents/pic";
        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            System.out.println("--------------------------body------------------------");
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            StringBuffer tempText = new StringBuffer();

            Map<String, Object> imageData = saveAllImage(absolutePath, document);


            List<Map<String, Object>> allInfo = new ArrayList<>();  //

            int typeCount = 0;
            int questionCount = 0;
            String regex_one = "r:id=\"(.*?)\"";
            String regex_two = "r:embed=\"(.*?)\"";
            for (int i = 0; i < paragraphs.size(); i++) {

                StringBuilder _html;
                BigInteger numIlvl = paragraphs.get(i).getNumIlvl();

                _html = new StringBuilder("<p class='" + paragraphs.get(i).getStyle() + " " + numIlvl + "'>");
                List<XWPFRun> runList = paragraphs.get(i).getRuns();
                for (XWPFRun xwpfRun : runList) {

                    Matcher m_one = getMatcher(xwpfRun.getCTR(), regex_one);
                    Matcher m_two = getMatcher(xwpfRun.getCTR(), regex_two);
                    if (m_one.find()) {
                        String imgPath = (String) imageData.get(m_one.group(1));
                        saveImg(imgPath, _html);
                    } else if (m_two.find()) {
                        String imgPath = (String) imageData.get(m_two.group(1));
                        saveImg(imgPath, _html);
                    } else if (xwpfRun.getSubscript().toString().equals("SUBSCRIPT")) {
                        _html.append("<span class='subscript'>" + xwpfRun + "</span>");
                    } else if (xwpfRun.getSubscript().toString().equals("SUPERSCRIPT")) {
                        _html.append("<span class='superscript'>").append(xwpfRun).append("</span>");
                    } else {
                        _html.append("<span> ").append(xwpfRun).append("</span>");
                    }

                    tempText.append(xwpfRun.toString());
                }
                _html.append("</p>");

                Map<String, Object> itemInfo = new HashMap<>();

                if (!"".equals(paragraphs.get(i).getStyle())) {
                    //判断一级标题---题目类型
                    if (judgmentType(paragraphs.get(i))) {
                        typeCount++;
                        questionCount = 0;
                        setItemInfo(typeCount, questionCount, paragraphs, i, _html, itemInfo, "questionType");
                    }
                }
                if (paragraphs.get(i).getNumIlvl() != null) {
//                    System.out.println(paragraphs.get(i).getNumIlvl() + "一二级标题");
                    //判断一级序列---题目内容
                    if (judgmentSequence(paragraphs, i, "0")) {
                        questionCount++;
                        setItemInfo(typeCount, questionCount, paragraphs, i, _html, itemInfo, "questionName");
                    }
                    //判断二级序列---题目选项
                    if (judgmentSequence(paragraphs, i, "1")) {
                        setItemInfo(typeCount, questionCount, paragraphs, i, _html, itemInfo, "questionOption");
                    }
                }

                /**
                 * 去除不必要的空行
                 */
                if (itemInfo.size() != 0) {
                    allInfo.add(itemInfo);
                }
            }

//            System.out.println(allInfo + "解析结果");

            // 设置初始值 用于循环判断结构
            int questionTypeCount = 0;
            int questionDetailsCount = (int) allInfo.get(0).get("questionCount");

            List<Map<String, Object>> questionTypes = new ArrayList<>();  //all
            Map<String, Object> questionType = new HashMap<>();   //试题类型 ---选择题、填空题、简答题
            List<Map<String, Object>> questionDetails = new ArrayList<>();   //每个试题类型的题目集合
            Map<String, Object> questionDetail = new HashMap<>();   //单个题目的详细信息
            List<Map<String, Object>> options = new ArrayList<>();    //每个题目的选项
            Map<String, Object> option;  //单个选项的信息

            for (int i = 0; i < allInfo.size(); i++) {
                int currentTypeCount = (int) allInfo.get(i).get("typeCount");
                if (currentTypeCount != questionTypeCount) {
                    questionType = new HashMap<>();
                    questionTypeCount = currentTypeCount;
                    questionDetailsCount = 0;
                    questionType.put("typeContent", allInfo.get(i).get("typeContent"));
                    questionType.put("type", allInfo.get(i).get("type"));
                    questionDetails = new ArrayList<>();
                } else {
                    if ((int) allInfo.get(i).get("questionCount") != questionDetailsCount) {
                        questionDetail = new HashMap<>();
                        questionDetail.put("content", allInfo.get(i).get("content"));
                        questionDetail.put("type", allInfo.get(i).get("type"));
                        questionDetailsCount = (int) allInfo.get(i).get("questionCount");
                        options = new ArrayList<>();
                    } else {
                        option = new HashMap<>();
                        option.put("type", allInfo.get(i).get("type"));
                        option.put("content", allInfo.get(i).get("content"));
                        options.add(option);
                    }

                }

                if (getNextCount(allInfo, i, "typeCount") != questionTypeCount) {
                    questionType.put("questionDetails", questionDetails);
                    questionTypes.add(questionType);
                }
                if (questionDetailsCount != 0 && getNextCount(allInfo, i, "questionCount") != questionDetailsCount) {
                    questionDetail.put("options", options);
                    questionDetails.add(questionDetail);
                }
                System.out.println(questionTypes +"第" + i + "次拼接" );

            }
            return questionTypes;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> saveAllImage(String absolutePath, XWPFDocument document) throws IOException {
        List<XWPFPictureData> pictures = document.getAllPictures();
        Map<String, Object> map = new HashMap<>();
        for (XWPFPictureData picture : pictures) {

            String id = picture.getParent().getRelationId(picture);
            File folder = new File(absolutePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String rawName = picture.getFileName();
            String fileExt = rawName.substring(rawName.lastIndexOf("."));
            String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;
            File saveFile = new File(absolutePath + File.separator + newName);
            @SuppressWarnings("resource")
            FileOutputStream fos = new FileOutputStream(saveFile);
            fos.write(picture.getData());
            System.out.println(id);
            System.out.println(saveFile.getAbsolutePath());
            map.put(id, saveFile.getAbsolutePath());
        }
        return map;
    }

    private static int getNextCount(List<Map<String, Object>> allInfo, int i, String typeCount) {
        return (i + 1 >= allInfo.size()) ? -1 : (int) allInfo.get(i + 1).get(typeCount);
    }

    private static void setItemInfo(int typeCount, int questionCount, List<XWPFParagraph> paragraphs, int i, StringBuilder _html, Map<String, Object> itemInfo, String questionType) {
        itemInfo.put("typeContent", paragraphs.get(i).getText());
        itemInfo.put("content", _html);
        itemInfo.put("type", questionType);
        itemInfo.put("typeCount", typeCount);
        itemInfo.put("questionCount", questionCount);

    }

    private static boolean judgmentSequence(List<XWPFParagraph> paragraphs, int i, String s) {
        return s.equals(paragraphs.get(i).getNumIlvl().toString().trim());
    }

    private static boolean judgmentType(XWPFParagraph xwpfParagraph) {
        return "1".equals(xwpfParagraph.getStyle()) || "Heading1".equals(xwpfParagraph.getStyle());
    }

    private static Matcher getMatcher(CTR xwpfRun, String regex) {
        Pattern p = Pattern.compile(regex, Pattern.DOTALL);
        return p.matcher(xwpfRun.toString());
    }

    private static void saveImg(String imgPath, StringBuilder _html) throws IOException {
        _html.append("<img src='").append(imgPath).append("' />");
    }
}
