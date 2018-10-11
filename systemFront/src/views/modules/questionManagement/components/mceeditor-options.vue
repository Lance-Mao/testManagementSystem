<template>
  <div>
    <el-card shadow="hover" class="box-card">
      <!-- 单选 多选-->
      <el-tag 
        type="success"
        size="medium">
        选项{{currentIndex + 1}}
      </el-tag>
      <el-row>
        <el-col :span="20">
          <textarea :id= "Id"></textarea>
        </el-col>
        <el-col :span="3" :offset="1">
          <el-radio v-model="radio.val" v-if="radio.type === 'singleSelection'" :label="currentIndex" @change="selectAnswerBySingle">选项{{currentIndex + 1}}</el-radio>
          <el-checkbox @change="selectAnswerByMultiple" :label="currentIndex" >选项{{currentIndex + 1}}</el-checkbox>
          <div>
            <el-button @click="plusOne" type="primary" size="mini" icon="el-icon-circle-plus"></el-button>
            <el-button @click="deleteOne" type="primary" size="mini" icon="el-icon-delete"></el-button>
          </div>
        </el-col>
        <br />
      </el-row>
    </el-card>
  </div>
</template>
<script>
  import '../../../../../static/tinymce/tinymce.min.js'
  export default {
    name: 'mceeditor',
    data () {
      const Id = Date.now() * Math.ceil(Math.random() * 1000)
      return {
        Id: Id,
        Editor: null,
        isCheck: false,
        DefaultConfig: {
          // GLOBAL
          language: 'zh_CN',
          height: 150,
          theme: 'modern',
          menubar: false,
          // toolbar: `styleselect | fontselect | formatselect | fontsizeselect | forecolor backcolor | bold italic underline strikethrough | image  media | table | alignleft aligncenter alignright alignjustify | outdent indent | numlist bullist | preview removeformat  hr | paste code  link | undo redo | fullscreen `,
          toolbar: `fontsizeselect | forecolor backcolor | bold italic underline strikethrough | image | table | outdent indent | numlist bullist | preview removeformat  hr | undo redo `,
          plugins: `
            paste
            importcss
            image
            code
            table
            advlist
            fullscreen
            link
            media
            lists
            textcolor
            colorpicker
            hr
            preview
          `,
  
          // CONFIG

          forced_root_block: 'p',
          force_p_newlines: true,
          importcss_append: true,

        // CONFIG: ContentStyle 这块很重要， 在最后呈现的页面也要写入这个基本样式保证前后一致， `table`和`img`的问题基本就靠这个来填坑了
          content_style: `
            *                         { padding:0; margin:0; }
            html, body                { height:100%; }
            img                       { max-width:100%; display:block;height:auto; }
            a                         { text-decoration: none; }
            iframe                    { width: 100%; }
            p                         { line-height:1.6; margin: 0px; }
            table                     { word-wrap:break-word; word-break:break-all; max-width:100%; border:none; border-color:#999; }
            .mce-object-iframe        { width:100%; box-sizing:border-box; margin:0; padding:0; }
            ul,ol                     { list-style-position:inside; }
          `,

          insert_button_items: 'image link | inserttable',

          // CONFIG: Paste
          paste_retain_style_properties: 'all',
          paste_word_valid_elements: '*[*]',        // word需要它
          paste_data_images: true,                  // 粘贴的同时能把内容里的图片自动上传，非常强力的功能
          paste_convert_word_fake_lists: false,     // 插入word文档需要该属性
          paste_webkit_styles: 'all',
          paste_merge_formats: true,
          nonbreaking_force_tab: false,
          paste_auto_cleanup_on_paste: false,

          // CONFIG: Font
          fontsize_formats: '10px 11px 12px 14px 16px 18px 20px 24px',

          // CONFIG: StyleSelect
          style_formats: [
            {
              title: '首行缩进',
              block: 'p',
              styles: { 'text-indent': '2em' }
            },
            {
              title: '行高',
              items: [
                {title: '1', styles: { 'line-height': '1' }, inline: 'span'},
                {title: '1.5', styles: { 'line-height': '1.5' }, inline: 'span'},
                {title: '2', styles: { 'line-height': '2' }, inline: 'span'},
                {title: '2.5', styles: { 'line-height': '2.5' }, inline: 'span'},
                {title: '3', styles: { 'line-height': '3' }, inline: 'span'}
              ]
            }
          ],

          // FontSelect
          font_formats: `
            微软雅黑=微软雅黑;
            宋体=宋体;
            黑体=黑体;
            仿宋=仿宋;
            楷体=楷体;
            隶书=隶书;
            幼圆=幼圆;
            Andale Mono=andale mono,times;
            Arial=arial, helvetica,
            sans-serif;
            Arial Black=arial black, avant garde;
            Book Antiqua=book antiqua,palatino;
            Comic Sans MS=comic sans ms,sans-serif;
            Courier New=courier new,courier;
            Georgia=georgia,palatino;
            Helvetica=helvetica;
            Impact=impact,chicago;
            Symbol=symbol;
            Tahoma=tahoma,arial,helvetica,sans-serif;
            Terminal=terminal,monaco;
            Times New Roman=times new roman,times;
            Trebuchet MS=trebuchet ms,geneva;
            Verdana=verdana,geneva;
            Webdings=webdings;
            Wingdings=wingdings,zapf dingbats`,

          // Tab
          tabfocus_elements: ':prev,:next',
          object_resizing: true,

          // Image
          imagetools_toolbar: 'rotateleft rotateright | flipv fliph | editimage imageoptions'
        }
      }
    },
    props: {
      value: {
        default: '',
        type: String
      },
      currentIndex: Number,
      // optionType: '',
      radio: null,
      config: {
        type: Object,
        default: () => {
          return {
            theme: 'modern',
            height: 100
          }
        }
      },
      url: {
        default: '',
        type: String
      },
      accept: {
        default: 'image/jpeg,image/png',
        type: String
      },
      maxSize: {
        default: 209715200,
        type: Number
      },
      withCredentials: {
        default: false,
        type: Boolean
      }
    },
    mounted () {
      this.init()
    },
    beforeDestroy () {
      // 销毁tinymce
      this.$emit('on-destroy')
      window.tinymce.remove(`#${this.Id}`)
    },
    watch: {
      value (val) {
        // debugger
        // window.tinymce.activeEditor.setContent(val)
      }
    },
    methods: {
      init () {
        const self = this
        this.Editor = window.tinymce.init({
          branding: false,
          // 默认配置
          ...this.DefaultConfig,
  
          // 图片上传
          images_upload_handler: function (blobInfo, success, failure) {
            if (blobInfo.blob().size > self.maxSize) {
              failure('文件体积过大')
            }
            // blobInfo.blob() 图片相关信息
            if (self.accept.split(',').includes(blobInfo.blob().type)) {
              uploadPic(blobInfo.blob())
            } else {
              failure('图片格式错误')
            }
            function uploadPic (pic) {
              let formData = new FormData()
              formData.append('file', blobInfo.blob(), blobInfo.filename())
              let config = {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              }
              self.$http.post(
                self.$http.adornUrl(`/questionManagement/questionpaper/uploadTestPicture`),
                formData,
                config
              ).then(data => {
                if (data.status === 200) {
                  success(data.data.url)
                }
              }).catch(err => {
                self.$emit('on-upload-fail')
                failure('上传失败: ' + err)
              })
            }
          },

          // prop内传入的的config
          ...this.config,
  
          // 挂载的DOM对象
          selector: `#${this.Id}`,
          setup: (editor) => {
            // 抛出 'on-ready' 事件钩子
            editor.on(
              'init', () => {
                self.loading = false
                self.$emit('on-ready')
                editor.setContent(self.value)
              }
            )
            // 抛出 'input' 事件钩子，同步value数据
            editor.on(
              'input change undo redo', () => {
                self.$emit('input', editor.getContent())
              }
            )
          }
        })
      },
      plusOne () {
        this.$emit('plusOne', this.currentIndex)
      },
      deleteOne () {
        this.$emit('deleteOne', this.currentIndex)
      },
      selectAnswerBySingle () {
        this.$emit('selectRadio', this.currentIndex)
      },
      selectAnswerByMultiple (e) {
        this.$emit('selectRadioByMultiple', {serialNumber: this.currentIndex, isCheck: e})
      }
    }
  }
</script>

<style>
 .box-card {
   margin-bottom: 5px;
 }
</style>
