<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="学员名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="学员名称"></el-input>
    </el-form-item>
    <el-form-item label="所属高校" prop="collegeid">
      <el-input v-model="dataForm.collegeid" placeholder="所属高校"></el-input>
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
          scid: 0,
          name: '',
          collegeid: ''
        },
        dataRule: {
          name: [
            { required: true, message: '学员名称不能为空', trigger: 'blur' }
          ],
          collegeid: [
            { required: true, message: '所属高校不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.scid = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.scid) {
            this.$http({
              url: this.$http.adornUrl(`/area/school/info/${this.dataForm.scid}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.school.name
                this.dataForm.collegeid = data.school.collegeid
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
              url: this.$http.adornUrl(`/area/school/${!this.dataForm.scid ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'scid': this.dataForm.scid || undefined,
                'name': this.dataForm.name,
                'collegeid': this.dataForm.collegeid
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
