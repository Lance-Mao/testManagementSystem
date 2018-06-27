<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="课程名称" prop="courseTitleId">
      <el-input v-model="dataForm.courseTitleId" placeholder="课程名称"></el-input>
    </el-form-item>
    <el-form-item label="知识点" prop="knowledgeTitleId">
      <el-input v-model="dataForm.knowledgeTitleId" placeholder="知识点"></el-input>
    </el-form-item>
    <el-form-item label="题型" prop="questionTypeId">
      <el-input v-model="dataForm.questionTypeId" placeholder="题型"></el-input>
    </el-form-item>
    <el-form-item label="录入者" prop="enterer">
      <el-input v-model="dataForm.enterer" placeholder="录入者"></el-input>
    </el-form-item>
    <el-form-item label="录入时间" prop="entryTime">
      <el-input v-model="dataForm.entryTime" placeholder="录入时间"></el-input>
    </el-form-item>
    <el-form-item label="更新时间" prop="updateTime">
      <el-input v-model="dataForm.updateTime" placeholder="更新时间"></el-input>
    </el-form-item>
    <el-form-item label="题目描述" prop="titleDescription">
      <el-input v-model="dataForm.titleDescription" placeholder="题目描述"></el-input>
    </el-form-item>
    <el-form-item label="答案" prop="answer">
      <el-input v-model="dataForm.answer" placeholder="答案"></el-input>
    </el-form-item>
    <el-form-item label="选项A" prop="answerA">
      <el-input v-model="dataForm.answerA" placeholder="选项A"></el-input>
    </el-form-item>
    <el-form-item label="选项B" prop="answerB">
      <el-input v-model="dataForm.answerB" placeholder="选项B"></el-input>
    </el-form-item>
    <el-form-item label="选项C" prop="answerC">
      <el-input v-model="dataForm.answerC" placeholder="选项C"></el-input>
    </el-form-item>
    <el-form-item label="选项D" prop="answerD">
      <el-input v-model="dataForm.answerD" placeholder="选项D"></el-input>
    </el-form-item>
    <el-form-item label="选项E" prop="answerE">
      <el-input v-model="dataForm.answerE" placeholder="选项E"></el-input>
    </el-form-item>
    <el-form-item label="选项F" prop="answerF">
      <el-input v-model="dataForm.answerF" placeholder="选项F"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          courseTitleId: '',
          knowledgeTitleId: '',
          questionTypeId: '',
          enterer: '',
          entryTime: '',
          updateTime: '',
          titleDescription: '',
          answer: '',
          answerA: '',
          answerB: '',
          answerC: '',
          answerD: '',
          answerE: '',
          answerF: ''
        },
        dataRule: {
          courseTitleId: [
            { required: true, message: '课程名称不能为空', trigger: 'blur' }
          ],
          knowledgeTitleId: [
            { required: true, message: '知识点不能为空', trigger: 'blur' }
          ],
          questionTypeId: [
            { required: true, message: '题型不能为空', trigger: 'blur' }
          ],
          enterer: [
            { required: true, message: '录入者不能为空', trigger: 'blur' }
          ],
          entryTime: [
            { required: true, message: '录入时间不能为空', trigger: 'blur' }
          ],
          updateTime: [
            { required: true, message: '更新时间不能为空', trigger: 'blur' }
          ],
          titleDescription: [
            { required: true, message: '题目描述不能为空', trigger: 'blur' }
          ],
          answer: [
            { required: true, message: '答案不能为空', trigger: 'blur' }
          ],
          answerA: [
            { required: true, message: '选项A不能为空', trigger: 'blur' }
          ],
          answerB: [
            { required: true, message: '选项B不能为空', trigger: 'blur' }
          ],
          answerC: [
            { required: true, message: '选项C不能为空', trigger: 'blur' }
          ],
          answerD: [
            { required: true, message: '选项D不能为空', trigger: 'blur' }
          ],
          answerE: [
            { required: true, message: '选项E不能为空', trigger: 'blur' }
          ],
          answerF: [
            { required: true, message: '选项F不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/questionManagement/questionquestionbank/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.courseTitleId = data.questionquestionbank.courseTitleId
                this.dataForm.knowledgeTitleId = data.questionquestionbank.knowledgeTitleId
                this.dataForm.questionTypeId = data.questionquestionbank.questionTypeId
                this.dataForm.enterer = data.questionquestionbank.enterer
                this.dataForm.entryTime = data.questionquestionbank.entryTime
                this.dataForm.updateTime = data.questionquestionbank.updateTime
                this.dataForm.titleDescription = data.questionquestionbank.titleDescription
                this.dataForm.answer = data.questionquestionbank.answer
                this.dataForm.answerA = data.questionquestionbank.answerA
                this.dataForm.answerB = data.questionquestionbank.answerB
                this.dataForm.answerC = data.questionquestionbank.answerC
                this.dataForm.answerD = data.questionquestionbank.answerD
                this.dataForm.answerE = data.questionquestionbank.answerE
                this.dataForm.answerF = data.questionquestionbank.answerF
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/questionManagement/questionquestionbank/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'courseTitleId': this.dataForm.courseTitleId,
                'knowledgeTitleId': this.dataForm.knowledgeTitleId,
                'questionTypeId': this.dataForm.questionTypeId,
                'enterer': this.dataForm.enterer,
                'entryTime': this.dataForm.entryTime,
                'updateTime': this.dataForm.updateTime,
                'titleDescription': this.dataForm.titleDescription,
                'answer': this.dataForm.answer,
                'answerA': this.dataForm.answerA,
                'answerB': this.dataForm.answerB,
                'answerC': this.dataForm.answerC,
                'answerD': this.dataForm.answerD,
                'answerE': this.dataForm.answerE,
                'answerF': this.dataForm.answerF
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
