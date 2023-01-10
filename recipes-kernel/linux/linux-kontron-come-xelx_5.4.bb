# linux-kontron-come-xelx.bb:

FILESEXTRAPATHS_prepend = "${THISDIR}/linux-kontron-come-xelx:"
LINUX_VERSION_EXTENSION = "-xelx"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.4.123"
LINUX_KERNEL_TYPE = "standard"

# This kernel uses the Intel-LTS kernel repository
KREPO = "git://github.com/intel/linux-intel-lts.git;protocol=https"
KBRANCH = "5.4/yocto"
KHASH ?= "7a821854911e315d3ed3fe2fc626e12906c9ab24"

# Alternative Yocto standard/base kernel repository
# Note: Untested! Provided patches might not cleanly apply
#KREPO = "git://git.yoctoproject.org/linux-yocto;protocol=git"
#KBRANCH = "v5.4/standard/base"
#KHASH ?= "${AUTOREV}"

KMETA = "kernel-meta"
KMETA_BRANCH = "yocto-5.4"
KMETA_SRC = "git://git.yoctoproject.org/yocto-kernel-cache"
KMETA_HASH ?= "78949176d073f5cf04c9e0c4be699e39528f2880"
# Uncomment this to automatically use the newest available commit
#KMETA_HASH = "${AUTOREV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRCREV_machine_pn-linux-kontron-come-xelx ?= "${KHASH}"
SRCREV_meta_pn-linux-kontron-come-xelx ?= "${KMETA_HASH}"

SRC_URI = "${KREPO};name=machine;branch=${KBRANCH};bareclone=1 \
           ${KMETA_SRC};type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI += "file://kontron-come-xelx-64-standard.scc \
            file://kontron-come-xelx-64.scc \
            file://kontron-come-xelx.cfg \
           "

PV = "${LINUX_VERSION}+git${SRCPV}"

INCLUDE_PATCHES := "${THISDIR}/linux-kontron-come-xelx/patches-5.4"

include linux-patches.inc

COMPATIBLE_MACHINE = "kontron-come-xelx-64"

include linux-common.inc
