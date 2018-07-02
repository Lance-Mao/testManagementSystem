package io.renren.modules.questionManagement.controller;

import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.utils.ResolveWordUtils;
import io.renren.common.utils.ShiroUtils;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;
import io.renren.modules.questionManagement.service.QuestionKnowledgePointService;
import io.renren.modules.questionManagement.service.QuestionPaperService;
import io.renren.modules.questionManagement.service.QuestionQuestionBankService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * 试题文件
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-19 10:24:01
 */
@RestController
@RequestMapping("questionManagement/questionpaper")
public class QuestionPaperController {
    @Autowired
    private QuestionPaperService questionPaperService;

    @Autowired
    private QuestionQuestionBankService questionQuestionBankService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("questionManagement:questionpaper:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = questionPaperService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 试题上传
     */
    @PostMapping("/upload")
    @RequiresPermissions("questionManagement:questionpaper:upload")
    public R upload(@RequestParam("file") MultipartFile file,@RequestParam Map<String, Object> params) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = Objects.requireNonNull(OSSFactory.build()).uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        QuestionPaperEntity questionPaperEntity = new QuestionPaperEntity();
        questionPaperEntity.setUploadAddress(url);
        questionPaperEntity.setDocumentType(suffix);
        questionPaperEntity.setCourseTitleId(Integer.parseInt((String) params.get("courseTitleId")));
        questionPaperEntity.setKnowledgePointId(Long.valueOf((String) params.get("knowledgePointId")));
        questionPaperEntity.setUploadBy(ShiroUtils.getUserId());

        List<Map<String, Object>> bankData = ResolveWordUtils.getData(file,questionPaperEntity);
        System.out.println(bankData + "解析数据");

        questionQuestionBankService.insertQuestionBank(bankData);
        questionPaperService.insert(questionPaperEntity);

        return R.ok().put("url", url);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("questionManagement:questionpaper:info")
    public R info(@PathVariable("id") Long id){
			QuestionPaperEntity questionPaper = questionPaperService.selectById(id);

        return R.ok().put("questionPaper", questionPaper);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("questionManagement:questionpaper:save")
    public R save(@RequestBody QuestionPaperEntity questionPaper){
			questionPaperService.insert(questionPaper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("questionManagement:questionpaper:update")
    public R update(@RequestBody QuestionPaperEntity questionPaper){
			questionPaperService.updateById(questionPaper);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("questionManagement:questionpaper:delete")
    public R delete(@RequestBody Long[] ids){
			questionPaperService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
