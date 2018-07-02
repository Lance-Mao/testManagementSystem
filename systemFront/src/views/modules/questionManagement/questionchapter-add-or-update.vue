<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="课程名称" prop="courseTitle">
      <el-cascader :style="{width: '100%'}"
          placeholder="课程"
          :options="options"
          @active-item-change="handleItemChange"
          filterable
          v-model="dataForm.courseTitle"
        ></el-cascader>
    </el-form-item>
    <el-form-item label="章节" prop="chapter">
      <el-input v-model="dataForm.chapter" placeholder="第?章"></el-input>
    </el-form-item>
    <el-form-item label="章节标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="章节标题"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  let _this
  export default {
    data () {
      return {
        visible: false,
        options: [],
        dataForm: {
          id: 0,
          chapter: '',
          title: '',
          courseTitle: null
        },
        dataRule: {
          chapter: [
            { required: true, message: '章节不能为空', trigger: 'blur' }
          ],
          title: [
            { required: true, message: '章节标题不能为空', trigger: 'blur' }
          ],
          courseTitle: [
            { required: true, message: '课程名称不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        _this = this
        // 清空数组，避免连续拼接
        this.options = []
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          this.$http({
            url: this.$http.adornUrl(`/questionManagement/questioncoursetitle/lists`),
            method: 'get',
            params: this.$http.adornParams({
              'isChild': 'no'
            })
          }).then(data => {
            if (data && data.data.code === 0) {
              _this.options.push(...data.data.list)
            }
          })
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/questionManagement/questionchapter/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.chapter = data.questionchapter.chapter
                this.dataForm.title = data.questionchapter.title
                this.dataForm.courseTitle = [data.questionchapter.courseTitle]
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
              url: this.$http.adornUrl(`/questionManagement/questionchapter/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'chapter': this.dataForm.chapter,
                'title': this.dataForm.title,
                'courseTitle': this.dataForm.courseTitle[0]
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
