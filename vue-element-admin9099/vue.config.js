 module.exports = {
    baseUrl: './',   // 根路径  ====》   baseUrl: '/',已废弃
    productionSourceMap: false,
    devServer: {
        port:9099,
        proxy: {
            '/baseUrl':{
                target:'http://localhost:9091/',
                changeOrigin:true,
                pathRewrite:{
                    '/baseUrl': ''
                }
            },
            '/api':{
                target:'http://localhost:9099/',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            },
            '/testApi':{
                target:'http://111.8.20.247:89/api/apph5api',
                changeOrigin:true,
                pathRewrite:{
                    '^/testApi':'/testApi'
                }
            },
            // '/ms':{
            //     target: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
            //     changeOrigin: true
            // }
        }
    }
}
