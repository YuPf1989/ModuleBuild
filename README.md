# ModuleBuild
组件化开发demo,练习组件化开发的上手项目，原作者项目地址：https://github.com/guiying712/AndroidModulePattern
，感兴趣的可以查看原项目。非常感谢原作者，对组件化开发有了一定的了解

## 项目说明

### 路由采用阿里的ARouter：具体用法请去看官方文档

### app组件功能（空壳工程）：

    配置整个项目的Gradle脚本，例如 混淆、签名等；
    app组件中可以初始化全局的库，例如Lib.init(this);
    业务组件管理（组装）；

### main组件功能（业务组件）：

    声明应用的launcherActivity----->android.intent.category.LAUNCHER；
    添加SplashActivity;
    添加LoginActivity；
    添加MainActivity；

### girls/news组件功能（业务组件）：

    这两个组件都是业务组件，根据产品的业务逻辑独立成一个组件；

### baselib组件功能（功能组件）：

    baselib组件是基础库，添加一些公用的类；
    例如：网络请求、图片加载、工具类、base类等等；
    声明APP需要的uses-permission；
    定义全局通用的主题（Theme）；
