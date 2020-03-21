# NavigionDemo

    第一次接触Navigion写的一入门教程
- 先来看看需要的东西把
        
        NavHost 这是一个容器存放要切换的碎片
        Fragment 这就是碎片
        NavController 这是导航管理者 用于设置切换路径
        NavGraph 这是导航图，存放切换路径

    <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_1.png"><img>

### 开始
    创建一个项目这些就不废话了
- 先创建两个Fragment(碎片)

        因为Navigion需要借助碎片进行切换
    - 第一个碎片

            和创建activity一样 包目录下-New-Fragment-Fragment(Blank)
            第一个碎片的文件分别两个 fragment_home.xml、HomeFragment.java
            注意在创建的时候的选择，这里看图,图中这个要去掉勾不然会自动生成一些乱七八糟的东西，我们初探导航还必须要这些东西。

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_2.png"><img>
    - 第二个碎片

            和第一个碎片差不多只是文件名字不一样而已
            第二个碎片的文件分别两个 fragment_detail.xml、DetailFragment.java
    - 编辑两个碎片的布局

            我习惯使用拖拽的方式来完成布局的基础操作
            两个碎片布局都是一样的一个TextView和一个Button
            随便怎么弄都可以也可以参考我的，红色框里的是TextView,绿色框里的是Button

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_8.png"><img>
   <span id="NavGraph"/>
    - 创建NavGraph

            NavGraph就是导航图，是属于资源,所以要创建就是在
            res目录下-New-Android Resource File
            名字随便
            Resource type选择Navigation
            然后点击图中的那个地方，进行添加，就会出现创建的两个碎片，将两个碎片添加进导航图中。

    <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_3.png"><img>
        
    - 添加切换路径

            这一步其实就是连线，按照图中的顺序连起来。

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_5.png"><img>
    - 扩展知识

            这一步可以跳过，先完成后面的步骤，看看这一步改了的东西和没改之前到底有什么变化.
        - 更改首页面

            这里将一下如何设定哪一个碎片为首页面，看图，图中的红色框里的那个Start就表示这个碎片是首页面，如果你在添加碎片的时候先添加的detail那个碎片的话你的首页面就是detail碎片了，那么怎么改呢，选择你想要的首页面碎片-右键-Set as Start Destination 这样首页面就更改了。
            现在来说一说图中黄色框里的是什么,教程开头提到了一个容器NavHost，黄色部分就是显示这个容器的，这里我们还没有创建所以还没有

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_4.png"><img>

        - 更改碎片标题
        
            这里说一下更改碎片的标题，选中你要更改的碎片，就右边看到图中的内容。
            图中红色框里的地方就是,更改标题的地方。

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_7.png"><img>

        - 添加切换动画

            让切换更生动，有视觉提醒，选择碎片之间连接的线就会在右边看到如图所示的编辑框红色框里的分别是设置启动时和关闭时的动画，黄色框里的是系统准备的动画，也可以自己写，这里不多说因为我不会。

        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_9.png"><img>
<span id="NavHost"/>
    - 添加 NavHost

            进入activity_main.xml里面，这里我还是使用的拖拽的方式，看图。
            将图中的那个NavHostFragment拖到布局中,注意这里我的根布局用的是ConstrainLayout，也是Androidstudio创建好项目布局文件的默认根布局,这个布局叫约束布局，把一个控件拖入需要将这个控件的上或下和左或右任意两边连接到四周，这里我上下左右分别连接的四周的边
            
        <img src="https://yi-sheep.github.io/NavigionDemo/Res/image/Navigation_6.png"><img>
    - 添加点击事件

            到这儿，你可以先运行一遍，你会发现点击并没有切换，进入两个碎片的java文件中
            直接上代码，注释很清楚。
        - HomeFragment.java
        ```java
        // 重写onActivityCreated()
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // 在这里面初始化控件最稳当避免空安全 因为这里是在碎片加载完后才调用的方法
            Button button = getView()  // 先获取到当前碎片的根布局文件
                    .findViewById(R.id.home_button);  // 再找到控件
            button.setOnClickListener(v -> {
                NavController controller = Navigation.findNavController(v); // 获取一个 导航管理者
                controller.navigate(R.id.action_homeFragment_to_detailFragment); // 设  置路线 这个id是my_nav.xml中的action标签的id就路线控件
            });
            // 上面这一大串还有简便的编写方法 在Detail碎片中就是要简便的
        }
        ```
        - DetailFragment.java
        ```java
        // 重写onActivityCreated()
        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            // 简便的方法
            getView()
                    .findViewById(R.id.detail_button)
                    .setOnClickListener(
                            Navigation.createNavigateOnClickListener(
                                    R.id.action_detailFragment_to_homeFragment
                            )
                    );
        }
        ```
    - 应用碎片的标题

            到这你也可以先运行一次，发现切换可以了，但是还有一个地方可以改一改，有没有想将应用显示的标题换成每一个碎片的标题呢？
        - MainActivity.java
        ```java
        private NavController mController;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mController = Navigation.findNavController(this,R.id.nav_host); // 获取管理者对象
            NavigationUI.setupActionBarWithNavController(this, mController); // 使用导航控制器设置操作栏
        }
        /**
        * 重写onSupportNavigateUp()
        * 设置标题栏上的返回逻辑
        * @return 上一个导航
        */
        @Override
        public boolean onSupportNavigateUp() {
            return mController.navigateUp(); // 回到上一步导航页面
        }
        ```
---
这项目是观看BiLiBiLi某个up主的视频编写的，这里附上视频地址.

[视频地址](https://www.bilibili.com/video/av57646155/?spm_id_from=333.788.videocard.0)

---
