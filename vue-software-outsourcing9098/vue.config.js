module.exports = {
    publicPath: './',   // 根路径  ====》   baseUrl: '/',已废弃
    outputDir: 'dist2', // 构建输出目录文件名打包时生成的名字。
    assetsDir: 'assets',   // 静态资源目录（js，css，img，fonts）
    lintOnSave: false,  // 是否开启eslint保存检测，有效值：true || false || ‘error’
    devServer: {
        open: true,      // 运行项目时，是否自动开启新窗口。
        host: '0.0.0.0',  // 手机测试端口号。//主机名字  locahost 或 127.0.0.0 或 真机测试 0.0.0.0
        port: 9098,     // 默认端口号
        https: false,      // 如果开启就会以https开头。
        hotOnly: false,      //安装模块更好的兼容，不需要配置。
        proxy: {
            // 配置跨域
            '/api': {
                target: 'http://localhost:9093/',  // 配置跨域接口
                ws: true,  // 是否跨域。
                changOrigin: true,// 是否跨域。
                pathRewrite: {
                    '^/api': ''
                }
            },
            // 配置跨域
            '/file': {
                target: 'http://localhost:80/',  // 配置跨域接口
                ws: true,  // 是否跨域。
                changOrigin: true,// 是否跨域。
                pathRewrite: {
                    '^/file': ''
                }
            }
        }
    }
};
