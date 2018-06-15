# Software Introduction

## You can get the whole diretory&files in "ALL.zip"

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

### Makefile
```shell
GCC = gcc
JAVAC = javac
JAVAH = javah
JAVA = java

WARNING = -Xlint:deprecation

IJAVAINCLUDE = -I$JAVA_HOME/include
IJAVAINCLUDElinux = -I$JAVA_HOME/include/linux

CFILE = lib/seismic/src_seismic_RungeKutta.c
OLIB = lib/seismic/libRungeKutta.so
LIBPATH = lib/seismic/

MAINCLASS = MainWindow

ALL:
	$(JAVAC) MainWindow.java $(WARNING)
	$(JAVAH) -d ./lib/ src.seismic.RungeKutta
	$(GCC) -shared -fpic -o $(OLIB) $(IJAVAINCLUDE) $(IJAVAINCLUDElinux) $(CFILE)
	$(JAVA) -Djava.library.path=$(LIBPATH) $(MAINCLASS)  &
clean:
	./~/clean.sh
```
## Software GUI
* Main Window
* 
![main window](resultPic/mainWindows.jpg)
* Buttons (from left to right)
* New Text
* 
![new Text](resultPic/newText.jpg)
* New Text with Edit
* 
![new Text with edit](resultPic/newTextWithEdit.jpg)
* Open Text
* 
![open Text](resultPic/openText.jpg)
* show a binary file into a picture
* 
![ximage](resultPic/ximage.jpg)
* in the next two is drawing ling and lines, pass it
* the next tow is Finite Difference (VTI media), for example
* 
![FD-vti](resultPic/FD-vti.jpg)
* it result:
* 
![snap](resultPic/snap.jpg)
* the next two is Ray tracing(isotropic and anisotropic)
* 
![raytracing](resultPic/raytracing.jpg)
* result 1: velocity is constant value
* 
![raypath](resultPic/raypath.jpg)
* result 2: velocity is not constant
* 
* ![raypath](resultPic/raypath2.jpg)

## Vertical Tool Bar
* first one is author (RongTao) introduction
* 
![author](resultPic/author.jpg)
