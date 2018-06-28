<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    @close="closeHandle"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="课程" prop="courseTitleId">
      <el-cascader :style="{width: '100%'}"
          placeholder="课程"
          :options="options"
          @active-item-change="handleItemChange"
          @change="handleItemChangeValue"
          filterable
          v-model="dataForm.courseTitleId"
        ></el-cascader>
    </el-form-item>
    <el-form-item label="文件" prop="courseTitleId" v-show="questionInfo.knowledgePointId">
      <el-upload
        drag
        :action="url"
        :data="questionInfo"
        :before-upload="beforeUploadHandle"
        :on-success="successHandle"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">只能上传word文件，且格式为.docx</div>
      </el-upload>
    </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
  let _this
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          courseTitleId: null,
          knowledgePointId: null
        },
        dataRule: {
          courseTitleId: [
            { required: true, message: '文档类型不能为空', trigger: 'blur' }
          ]
        },
        options: [],
        url: '',
        questionInfo: {
          courseTitleId: '',
          knowledgePointId: ''
        }
      }
    },
    methods: {
      init (id) {
        _this = this
        // 清空数组，避免连续拼接
        this.options = []
        this.questionInfo = {
          courseTitleId: '',
          knowledgePointId: ''
        }
        // 设置试题文档上传地址
        this.url = this.$http.adornUrl(`/questionManagement/questionpaper/upload?token=${this.$cookie.get('token')}`)
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
        })
      },
      // 上传之前
      beforeUploadHandle (file) {
        console.log(file, '文件类型')
        if (file.type !== 'application/vnd.openxmlformats-officedocument.wordprocessingml.document') {
          this.$message.error('只支持.docx格式的word文档！')
          return false
        }
        this.num++
      },
      // 上传成功
      successHandle (response, file) {
        this.successNum++
        console.log(response, '上传完成')
        if (response && response.code === 0) {
          this.$confirm('操作成功, 是否继续操作?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).catch(() => {
            this.visible = false
          })
        } else {
          this.$message.error(response.msg)
        }
      },
      // 弹窗关闭时
      closeHandle () {
        console.log(123456)
        this.$emit('refreshDataList')
      },
      handleItemChange (e) {
        _this.options.find(item => item.value === e[0]).children = []
        this.$http({
          url: this.$http.adornUrl(`/questionManagement/questionknowledgepoint/lists`),
          method: 'get',
          params: this.$http.adornParams({
            'isChild': 'no',
            'id': e[0]
          })
        }).then((data) => {
          _this.options.find(item => item.value === e[0]).children.push(...data.data.list)
        })
      },
      handleItemChangeValue (e) {
        if (e.length === 2) {
          _this.questionInfo.courseTitleId = e[0]
          _this.questionInfo.knowledgePointId = e[1]
        }
      }
    }
  }
</script>
