<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="课程" prop="chapter">
      <el-cascader :style="{width: '100%'}"
          placeholder="课程"
          :options="options"
          @active-item-change="handleItemChange"
          filterable
          v-model="dataForm.chapter"
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
          chapter: null
        },
        dataRule: {
          knowledgePoint: [
            { required: true, message: '知识点不能为空', trigger: 'blur' }
          ],
          chapter: [
            { required: true, message: '课程不能为空', trigger: 'blur' }
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
              'isChild': 'yes'
            })
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
                console.log(data)
                this.dataForm.knowledgePoint = data.questionKnowledgePoint.knowledgePoint
                this.dataForm.chapter = [data.questionKnowledgePoint.chapter]
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
                'chapter': this.dataForm.chapter[0]
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
        console.log(e[0])
        _this.dataForm.chapter = e
        this.$http({
          url: this.$http.adornUrl(`questionManagement/questionpaper/lists`),
          mothod: 'get',
          params: {
            'isChild': 'no',
            'id': e[0]
          }
        }).then(data => {
          console.log(data, '接收的数据')
          _this.options.find(item => item.value === e[0].children.push(...data.data.list))
        })
      }
    }
  }
</script>
