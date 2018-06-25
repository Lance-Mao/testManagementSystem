<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="编码" prop="provinceid">
      <el-input v-model="dataForm.provinceid" placeholder="编码"></el-input>
    </el-form-item>
    <el-form-item label="省份名称" prop="pname">
      <el-input v-model="dataForm.pname" placeholder="省份名称"></el-input>
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
          pid: 0,
          provinceid: '',
          pname: ''
        },
        dataRule: {
          provinceid: [
            { required: true, message: '编码不能为空', trigger: 'blur' }
          ],
          pname: [
            { required: true, message: '省份名称不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.pid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.pid) {
            this.$http({
              url: this.$http.adornUrl(`/area/regionprovince/info/${this.dataForm.pid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.provinceid = data.regionprovince.provinceid
                this.dataForm.pname = data.regionprovince.pname
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
              url: this.$http.adornUrl(`/area/regionprovince/${!this.dataForm.pid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'pid': this.dataForm.pid || undefined,
                'provinceid': this.dataForm.provinceid,
                'pname': this.dataForm.pname
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
