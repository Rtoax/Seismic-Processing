This is a software that contains seismic forward modeling, ray tracing, text editing and plotting. You can get the whole diretory&files in ```ALL.zip```
这是一个包含了地震正演模拟，射线追踪，文本编辑和绘制曲线图的软件

## Directory & file tree in file "DirTree.md"

* simple directory tree as follows(Detail in DirTree.md)

```shell
root
├── clean.sh
├── Compile.sh
├── lib
│   ├── include
│   └── seismic
├── MainWindow.java
├── Makefile
├── net
│   └── java
│       └── dev
│           └── designgridlayout
├── picture
│   ├── Author
│   ├── BackGround
│   ├── ButtonImg
│   └── Expression
└── src
    ├── about
    ├── menu
    ├── myComponent
    ├── paint
    ├── seismic
    ├── swap
    └── text

20 directories, 225 files
```

## Compiled & RUN: 

```shell
$ make # or
$ sh Compile.sh
```

* You can use clean.sh to clean all .class and other temp

```shell
$ sh clean.sh
```

### Denpendence

* JDK 8+
* JNI
* gcc
* Linux is the best env.

## Software GUI

* RayTracing 射线追踪

![raypath](raypath.jpg)

* Main Window
* 
![main window](screenshot/mainWindows.jpg)
* Buttons (from left to right)
* New Text
* 
![new Text](screenshot/newText.jpg)
* New Text with Edit
* 
![new Text with edit](screenshot/newTextWithEdit.jpg)
* Open Text
* 
![open Text](screenshot/openText.jpg)
* show a binary file into a picture
* 
![ximage](screenshot/ximage.jpg)
* in the next two is drawing ling and lines, pass it
* the next tow is Finite Difference (VTI media), for example
* 
![FD-vti](screenshot/FD-vti.jpg)
* it result:
* 
![snap](screenshot/snap.jpg)
* the next two is Ray tracing(isotropic and anisotropic)
* 
![raytracing](screenshot/raytracing.jpg)
* result 1: velocity is constant value
* 
![raypath](screenshot/raypath.jpg)
* result 2: velocity is not constant
* 
* ![raypath](screenshot/raypath2.jpg)

## Vertical Tool Bar
* first one is author (RongTao) introduction
* 
![author](screenshot/author.jpg)
