# ns-album-helper
A tool to help you filter screenshots and videos from Nintendo Switch by game names on your PC or Mac

![Demo](demo/NSAH-Demo.png "optional title")


# NS Album Helper NS相册分类帮手

## 下载链接
### 软件本体
 - macOS：[NSAH_macos_1_2.dmg](Release/NSAH_macos_1_2.dmg)
 - Windows：[NSAH_windows-x64_1_2.exe](Release/NSAH_windows-x64_1_2.exe)
### 配置文件
 - 通用配置文件[id_name_map.csv](Release/id_name_map.csv). 
注意：配置文件通用，以上两个版本为安装包模式，内含所需运行环境（Java的JRE）。如果你电脑里有jdk 1.8以上版本，可以直接尝试运行Release文件夹中的jar或自行编译。

## 操作说明

1、本程序帮助您将NS外置SD卡中的截图和视频按”游戏“分类导出，并保留时间属性。

2、软件的使用顺序是：

①设置好相册目录、配置文件、导出目录。

相册目录的名称通常是Nintendo文件夹中的“Album”文件夹。

配置文件是一个游戏ID和名称对应的CVS格式文件（见附件）。

导出目录可以多次使用。多次导入时，为增量导入，即仅导入新增的截图和视频。

②点击“开始检查”按钮，检查到的游戏将会显示在下方列表。

③单击选中一行，点击“查看选中行示例”，可用系统软件打开最后一个媒体文件，方便分辨游戏以自定名称。

④双击某个单元格，修改游戏列表中的名称，未知的游戏默认名称为“null”。回车键或单击表中其他行或空白处临时确认修改。

⑤确认游戏名称无误后，点击“保存配置”按钮来二次确认，新的配置将会覆盖保存在原配置文件中，方便下次使用。

⑥点击“开始导出”按钮，软件将会依照当前配置文件按游戏导出截图和视频。

注意：如果您的内容较多（例如从来没分过类），可能需要花费数十分钟时间，请耐心等待至出现“导出完毕”对话框出现为止。

## 关于更多
软件作者：@hshsilver

数据综合自GitHub

本程序免费开源



## 更新记录

Version 1.0 2020.04.08 软件基本功能完成。

Version 1.1 2020.04.09 小修小补，更新内容：
 - CSV文件写入时加入BOM文件头，防止Excel读取CSV时乱码。
 - 拷贝时保留截图和视频本身的时间属性。

Version 1.2 2020.04.10 修正Bug：
 - 如果游戏名称中有英文冒号，则去掉，防止文件夹创建时出错或冒号变为其他符号。