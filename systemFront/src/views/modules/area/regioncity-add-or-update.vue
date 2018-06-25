<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="编码" prop="cityid">
      <el-input v-model="dataForm.cityid" placeholder="编码"></el-input>
    </el-form-item>
    <el-form-item label="城市名称" prop="city">
      <el-input v-model="dataForm.city" placeholder="城市名称"></el-input>
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
          cid: 0,
          cityid: '',
          city: '',
          provinceid: ''
        },
        dataRule: {
          cityid: [
            { required: true, message: '编码不能为空', trigger: 'blur' }
          ],
          city: [
            { required: true, message: '城市名称不能为空', trigger: 'blur' }
          ],
          provinceid: [
            { required: true, message: '所属省份不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.cid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.cid) {
            this.$http({
              url: this.$http.adornUrl(`/area/regioncity/info/${this.dataForm.cid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.cityid = data.regioncity.cityid
                this.dataForm.city = data.regioncity.city
                this.dataForm.provinceid = data.regioncity.provinceid
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
              url: this.$http.adornUrl(`/area/regioncity/${!this.dataForm.cid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'cid': this.dataForm.cid || undefined,
                'cityid': this.dataForm.cityid,
                'city': this.dataForm.city,
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
