require recipes-graphics/xorg-driver/xorg-driver-video.inc

SUMMARY = "X.Org X server -- AMD graphics chipsets driver"

DESCRIPTION = "xf86-video-amd is an Xorg driver for AMD integrated	\
graphics chipsets. The driver supports depths 8, 15, 16 and 24. On	\
some chipsets, the driver supports hardware accelerated 3D via the	\
Direct Rendering Infrastructure (DRI)."

LIC_FILES_CHKSUM = "file://COPYING;md5=aabff1606551f9461ccf567739af63dc"

DEPENDS += "virtual/libx11 drm virtual/libgl  libpciaccess \
"

PACKAGECONFIG[udev] = "--enable-udev,--disable-udev,udev"
PACKAGECONFIG[glamor] = "--enable-glamor,--disable-glamor"
PACKAGECONFIG_append = " udev glamor"

SRC_URI = "git://anongit.freedesktop.org/xorg/driver/xf86-video-amdgpu \
           "
SRCREV = "b467d2569a003da05ad222b0dc095bee5eec450a"
PV = "19.1.0+git${SRCPV}"
S = "${WORKDIR}/git"

RDEPENDS_${PN} += "mesa-driver-radeon \
		   mesa-driver-radeonsi \
		   mesa-driver-swrast \
"

COMPATIBLE_HOST = '(i.86|x86_64).*-linux'

PACKAGES += "${PN}-conf"
FILES_${PN}-conf = "${datadir}/X11"
