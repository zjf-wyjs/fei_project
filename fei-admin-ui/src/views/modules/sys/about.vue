<template>
  <div>
    <el-button style="float: right" @click="save">保存</el-button>
    <!-- 工具栏容器 -->
    <div id="toolbar-container"></div>

    <!-- 编辑器容器 -->
    <div id="editor" style="height: 1000px">
      <p>This is the initial editor content.</p>
    </div>
  </div>
</template>

<script>
import CKEditor from '@ckeditor/ckeditor5-build-decoupled-document'
import '@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn'

export default {
  name: 'about',
  data () {
    return {
      editor: null// 编辑器实例
    }
  },
  mounted () {
    this.initCKEditor()
  },
  methods: {
    save () {
      this.$http({
        url: this.$http.adornUrl('/sys/about/save'),
        method: 'post',
        data: {
          'content': this.editor.getData()
        }
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.$message.success('保存成功')
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    initCKEditor () {
      CKEditor.create(
        document.querySelector('#editor'), {
          language: 'zh-cn',
          ckfinder: {
            uploadUrl: '/admin/Upload/uploadUrl'
            // 后端处理上传逻辑返回json数据,包括uploaded(选项true/false)和url两个字段
          }
        }).then(editor => {
          const toolbarContainer = document.querySelector('#toolbar-container')
          toolbarContainer.appendChild(editor.ui.view.toolbar.element)
          this.editor = editor // 将编辑器保存起来，用来随时获取编辑器中的内容等，执行一些操作
        }).catch(error => {
          console.error(error)
        })

      this.$http({
        url: this.$http.adornUrl('/sys/about/info'),
        method: 'get',
        data: this.$http.adornData(this.dataForm)
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.editor.setData(data.info.content)
        } else {
          this.$message.error(data.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
