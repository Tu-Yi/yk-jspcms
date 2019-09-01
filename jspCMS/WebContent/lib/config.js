/**
 * 通用配置
 * Created by gameloft9 on 2018/7/19.
 */
/*开发环境*/
var runEnv = 'product';

// $config不建议直接引用，除了用于指定模块基目录。可以通过$tool模块中的方法获取
var $config = {
    apiContext: runEnv === 'product' ? 'http://118.24.175.34:8080/jspCMS/' : 'http://localhost:8080/jspCMS/', // api请求地址
    resUrl: runEnv === 'product' ? 'http://118.24.175.34:8080/jspCMS/' : 'http://localhost:8080/jspCMS/', // 前端资源访问根目录,生产环境请设置为托管前端资源的位置
    default_password: '123456'
};

