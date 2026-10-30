# CampusLife 实现说明

这个工程对应 COMPX202 / Y05853 的 Assignment One。工程名保留为 AssignmentOne，手机桌面上显示的应用名是 CampusLife。应用代码使用 Java，界面使用 XML，没有使用 Compose。

## 主要文件怎么读

| 文件 | 作用 |
|---|---|
| `MainActivity.java` | 加载首页，处理四个图片按钮的点击、Toast 和页面跳转 |
| `DetailActivity.java` | 读取 Intent 中的类别编号，使用三个数组填充详情页 |
| `res/layout/activity_main.xml` | 默认竖屏首页，四个按钮排成 2×2 |
| `res/layout-land/activity_main.xml` | 横屏首页，四个按钮排成一行 |
| `res/layout/activity_detail.xml` | 四类内容共用的详情页布局 |
| `res/values/arrays.xml` | 老师提供的标题、正文和图片名称，三个数组各有四项 |
| `res/drawable/topic_button_selector.xml` | 正常绿色、按下浅绿色的按钮状态资源 |
| `res/values-v27/themes.xml` | 仅在 API 27 及以上使用的导航栏图标样式 |

## 为什么不需要 if 或 switch

四个按钮的 XML `tag` 分别是 0、1、2、3，依次表示 Facilities、Events、Clubs、Support。竖屏与横屏的编号保持一致。

按钮点击后调用公共的 `openDetail()` 方法。这个方法先显示 Toast，再创建指向 `DetailActivity.class` 的显式 Intent，把编号和类别名称放入 extras。

详情页在 `onCreate()` 中取得编号，然后用同一个编号读取三个数组。例如编号 2 对应社团，直接取 `titles[2]`、`content[2]` 和 `imageNames[2]`。选择数据不需要逐个判断类别。

原始图片数组存储的是 `img_btn_clubs` 这样的资源名称，而不是整数资源 ID，所以这里使用 `getResources().getIdentifier()` 将名称转换为图片资源 ID。代码里的 `SuppressLint` 只针对这一处有意采用的资源名称查找，不关闭兼容性检查。

## 布局为什么能适应屏幕

首页的上半部分为校园照片横幅，下半部分为服务入口。`layout_weight` 按当前可用空间分配区域，不把按钮写成固定宽高。

配合权重使用的 `0dp` 表示由剩余空间决定该方向的大小，不是把控件固定成零大小。文字标签使用 `wrap_content` 保留所需高度，图片使用剩余空间。

横幅标题按要求使用 40sp。在屏幕很矮或字体放大时，横幅中的文字区域可以滚动。详情页正文也可以滚动，因此长篇社团、学生支持内容不会被截断。

横屏资源与竖屏资源使用相同文件名和按钮 ID。旋转时 Android 自动选择 `layout-land`，Java 代码不需要手动判断横竖屏。详情页重建时仍从原来的 Intent 读取编号，所以会显示同一个类别。

系统栏和刘海占用的空间由 WindowInsets 提供，代码按实际返回值设置内边距，没有写死状态栏高度。

## 注释和资源约定

Java 与 XML 的关键位置已添加英文注释，说明权重、点击方法签名、类别顺序、Intent 数据和动态图片查找的用途。注释重点解释实现原因。

原压缩包中的 `array.xml` 被改名为 `arrays.xml`，内容保持原样。图片放入 `drawable-nodpi`，由 ImageView 按实际区域缩放。应用图标提供普通版本、多个密度版本及自适应图标。

API 27 才支持的导航栏属性位于 `values-v27`。最低版本仍是 API 24。`local.properties`、构建输出、IDE 本地设置及签名密钥均不会提交到仓库。

## 提交前检查

- 在 Android Studio 打开仓库根目录并完成 Gradle Sync。
- 运行 `app`，检查四个入口、详情页、滚动、返回与旋转。
- 用 `gradlew.bat :app:assembleDebug :app:lintDebug` 进行构建和静态检查。
- 确认 GitHub 上包含最新的全部提交；Canvas 提交仓库地址。

Git 提交按 A—Ⅰ 的实际开发阶段保存，提交时间为真实执行时间。
