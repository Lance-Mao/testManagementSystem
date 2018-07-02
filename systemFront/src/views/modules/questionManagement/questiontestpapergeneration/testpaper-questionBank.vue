<template>
    <div class="block">
        <span class="demonstration">选择课程类别：</span>
        <el-cascader
            :options="options"
            v-model="selectedOptions"
            @change="handleChange">
        </el-cascader>
    </div>
</template>

<script>
let _this
export default {
  data () {
    return {
      options: [],
      selectedOptions: [],
      selectedOptions2: []
    }
  },
  methods: {
    init () {
      _this = this
      this.$http({
        url: this.$http.adornUrl('/questionManagement/questioncoursetitle/lists'),
        method: 'get',
        params: this.$http.adornParams({
          'isChild': 'no'
        })
      }).then(data => {
        console.log(data, '接收的数据')
        if (data && data.data.code === 0) {
          _this.options = data.data.list
        }
      })
    },
    handleChange (value) {
      console.log(value)
    }
  }
}
</script>

