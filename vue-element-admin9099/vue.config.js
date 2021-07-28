 module.exports = {
    baseUrl: './',
    productionSourceMap: false,
    devServer: {
        port:9099,
        proxy: {
            '/business':{
                target:'http://localhost:9091/',
                changeOrigin:true,
                pathRewrite:{
                    '/business': ''
                }
            },
            '/api':{
                target:'http://localhost:9099/',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            },
            '/test':{
                target:'http://localhost:9091/',
                changeOrigin:true,
                pathRewrite:{
                    '/test':''
                }
            },
            '/testApi':{
                target:'http://111.8.20.247:89/api/apph5api',
                changeOrigin:true,
                pathRewrite:{
                    '^/testApi':'/testApi'
                }
            },
            '/ms':{
                target: 'https://www.easy-mock.com/mock/592501a391470c0ac1fab128',
                changeOrigin: true
            }
        }
    }
}
