package io.renren.common.utils;

import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
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

    public static List<Map<String, Object>> getData(MultipartFile file,QuestionPaperEntity questionPaperEntity) {
        String absolutePath = "/home/lancemao/Documents/pic";
        try {
            XWPFDocument document = new XWPFDocument(file.getInputStream());
            System.out.println("--------------------------body------------------------");
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            StringBuffer tempText = new StringBuffer();

            Map<String, Object> imageData = saveAllImage(absolutePath, document);


            List<Map<String, Object>> allInfo = new ArrayList<>();  //所有题
            List<Map<String, Object>> allInfoAnswer = new ArrayList<>();  //所有题对应的答案

            int typeCount = 0;
            int questionCount = 0;
            String regex_one = "r:id=\"(.*?)\"";
            String regex_two = "r:embed=\"(.*?)\"";
            boolean isAnswer = false;  //判断是否进入答案部分
            for (int i = 0; i < paragraphs.size(); i++) {

                StringBuilder _html;
                BigInteger numIlvl = paragraphs.get(i).getNumIlvl();

                _html = new StringBuilder();
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
                        _html.append("###").append("<subscript>" + xwpfRun + "</subscript>");
                    } else if (xwpfRun.getSubscript().toString().equals("SUPERSCRIPT")) {
                        _html.append("###").append("<superscript>").append(xwpfRun).append("</superscript>");
                    } else {
                        _html.append(xwpfRun);
                    }

                    tempText.append(xwpfRun.toString());
                }

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

                if ((int) itemInfo.get("questionCount") == 0 && itemInfo.get("typeContent").toString().trim().equals("答案")) {
                    isAnswer = true;
                }
                /**
                 * 去除不必要的空行
                 */
                if (itemInfo.size() != 0) {
                    if (isAnswer) {
                        allInfoAnswer.add(itemInfo);
                    } else {
                        allInfo.add(itemInfo);
                    }
                }
            }

            // 设置初始值 用于循环判断结构
            int questionTypeCount = 0;
            int questionDetailsCount = (int) allInfo.get(0).get("questionCount");

            List<Map<String, Object>> questionTypes = new ArrayList<>();  //all
            Map<String, Object> questionType = new HashMap<>();   //试题类型 ---选择题、填空题、简答题
            List<Map<String, Object>> questionDetails = new ArrayList<>();   //每个试题类型的题目集合
            Map<String, Object> questionDetail = new HashMap<>();   //单个题目的详细信息
            List<Map<String, Object>> options = new ArrayList<>();    //每个题目的选项
            Map<String, Object> option;  //单个选项的信息

            int option_item = 64; //用于记录选项
            int answer_item = 0;  //用于记录答案的位置
            for (int i = 0; i < allInfo.size(); i++) {
                int currentTypeCount = (int) allInfo.get(i).get("typeCount");
                if (currentTypeCount != questionTypeCount) {
                    answer_item++;
                    questionType = new HashMap<>();
                    questionTypeCount = currentTypeCount;
                    questionDetailsCount = 0;
                    questionType.put("typeContent", allInfo.get(i).get("typeContent"));
                    questionType.put("type", allInfo.get(i).get("type"));
                    questionType.put("enterer", questionPaperEntity.getUploadBy());
                    questionType.put("courseTitleId", questionPaperEntity.getCourseTitleId());
                    questionType.put("knowledgeTitleId", questionPaperEntity.getKnowledgePointId());
                    questionDetails = new ArrayList<>();
                } else {
                    if ((int) allInfo.get(i).get("questionCount") != questionDetailsCount) {
                        option_item = 64; //用于初始化，重新记录下一轮选项
                        answer_item++;
                        questionDetail = new HashMap<>();
                        questionDetail.put("content", allInfo.get(i).get("content"));
                        questionDetail.put("type", allInfo.get(i).get("type"));
                        questionDetail.put("answer", allInfoAnswer.get(answer_item).get("content"));
                        questionDetail.put("answer_A", "---");
                        questionDetail.put("answer_B", "---");
                        questionDetail.put("answer_C", "---");
                        questionDetail.put("answer_D", "---");
                        questionDetail.put("answer_E", "---");
                        questionDetail.put("answer_F", "---");
                        questionDetailsCount = (int) allInfo.get(i).get("questionCount");
                    } else {
                        option_item++;
                        questionDetail.put("answer_" + byteAsciiToChar(option_item), allInfo.get(i).get("content"));
                    }

                }

                if (getNextCount(allInfo, i, "typeCount") != questionTypeCount) {
                    questionType.put("questionDetails", questionDetails);
                    questionTypes.add(questionType);
                }
                if (questionDetailsCount != 0 && getNextCount(allInfo, i, "questionCount") != questionDetailsCount) {
                    questionDetails.add(questionDetail);
                }

            }
            return questionTypes;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存图片
     * @param absolutePath
     * @param document
     * @return
     * @throws IOException
     */
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

    /**
     * 根据数字转换对应的ascii
     *
     * @param ascii
     * @return
     */
    public static char byteAsciiToChar(int ascii) {
        char ch = (char) ascii;
        return ch;

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
        _html.append("###").append("<image>").append(imgPath).append("</image>");
    }
}
