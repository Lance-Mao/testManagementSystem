<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="高校名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="高校名称"></el-input>
    </el-form-item>
    <el-form-item label="所属省份" prop="provinceid">
      <el-input v-model="dataForm.provinceid" placeholder="所属省份"></el-input>
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
          coid: 0,
          name: '',
          provinceid: ''
        },
        dataRule: {
          name: [
            { required: true, message: '高校名称不能为空', trigger: 'blur' }
          ],
          provinceid: [
            { required: true, message: '所属省份不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.coid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.coid) {
            this.$http({
              url: this.$http.adornUrl(`/area/college/info/${this.dataForm.coid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.college.name
                this.dataForm.provinceid = data.college.provinceid
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
              url: this.$http.adornUrl(`/area/college/${!this.dataForm.coid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'coid': this.dataForm.coid || undefined,
                'name': this.dataForm.name,
                'provinceid': this.dataForm.provinceid
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
