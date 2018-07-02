<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="试卷名称" prop="testName">
      <el-input v-model="dataForm.testName" placeholder="试卷名称"></el-input>
    </el-form-item>
    <el-form-item label="出题人" prop="examiner">
      <el-input v-model="dataForm.examiner" placeholder="出题人"></el-input>
    </el-form-item>
    <el-form-item label="出题时间" prop="timeOfQuestion">
      <el-input v-model="dataForm. timeOfQuestion" placeholder="出题时间"></el-input>
    </el-form-item>
    <el-form-item label="试卷内容" prop="testContent">
      <el-input v-model="dataForm.testContent" placeholder="试卷内容"></el-input>
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
          courseTitle: 0,
          testName: '',
          examiner: '',
          timeOfQuestion: '',
          testContent: ''
        },
        dataRule: {
          testName: [
            { required: true, message: '试卷名称不能为空', trigger: 'blur' }
          ],
          examiner: [
            { required: true, message: '出题人不能为空', trigger: 'blur' }
          ],
          timeOfQuestion: [
            { required: true, message: '出题时间不能为空', trigger: 'blur' }
          ],
          testContent: [
            { required: true, message: '试卷内容不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.courseTitle = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.courseTitle) {
            this.$http({
              url: this.$http.adornUrl(`/questionManagement/questiontestpapergeneration/info/${this.dataForm.courseTitle}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.testName = data.questiontestpapergeneration.testName
                this.dataForm.examiner = data.questiontestpapergeneration.examiner
                this.dataForm.timeOfQuestion = data.questiontestpapergeneration.timeOfQuestion
                this.dataForm.testContent = data.questiontestpapergeneration.testContent
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
              url: this.$http.adornUrl(`/questionManagement/questiontestpapergeneration/${!this.dataForm.courseTitle ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'courseTitle': this.dataForm.courseTitle || undefined,
                'testName': this.dataForm.testName,
                'examiner': this.dataForm.examiner,
                'timeOfQuestion': this.dataForm.timeOfQuestion,
                'testContent': this.dataForm.testContent
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
