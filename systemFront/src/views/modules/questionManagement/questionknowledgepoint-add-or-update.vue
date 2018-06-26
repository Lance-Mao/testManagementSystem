<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
      <el-form-item label="课程" prop="courseTitleId">
      <el-input v-model="dataForm.courseTitleId" placeholder="课程"></el-input>
    </el-form-item>
    <el-form-item>
      <el-cascader :style="{width: '100%'}"
          placeholder="搜索"
          :options="options"
          @active-item-change="handleItemChange"
          filterable
        ></el-cascader>
    </el-form-item>
    <el-form-item label="知识点" prop="knowledgePoint">
      <el-input v-model="dataForm.knowledgePoint" placeholder="知识点"></el-input>
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
          knowledgePoint: '',
          courseTitleId: ''
        },
        dataRule: {
          knowledgePoint: [
            { required: true, message: '知识点不能为空', trigger: 'blur' }
          ],
          courseTitleId: [
            { required: true, message: '课程不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        _this = this
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          this.$http({
            url: this.$http.adornUrl(`/questionManagement/questioncoursetitle/lists`),
            method: 'get'
          }).then(data => {
            if (data && data.data.code === 0) {
              _this.options.push(...data.data.list)
            }
          })
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/questionManagement/questionknowledgepoint/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.knowledgePoint = data.questionknowledgepoint.knowledgePoint
                this.dataForm.courseTitleId = data.questionknowledgepoint.courseTitleId
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
              url: this.$http.adornUrl(`/questionManagement/questionknowledgepoint/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'knowledgePoint': this.dataForm.knowledgePoint,
                'courseTitleId': this.dataForm.courseTitleId
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
      },
      handleItemChange (e) {
        console.log(e)
      }
    }
  }
</script>
