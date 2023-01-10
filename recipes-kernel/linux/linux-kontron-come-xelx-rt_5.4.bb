# linux-kontron-come-xelx-rt.bb:

FILESEXTRAPATHS_prepend = "${THISDIR}/linux-kontron-come-xelx:"
LINUX_VERSION_EXTENSION = "-xelx"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.4.123"
LINUX_KERNEL_TYPE = "preempt-rt"

# This kernel uses the Intel-LTS kernel repository
KREPO = "git://github.com/intel/linux-intel-lts.git;protocol=https"
KBRANCH = "5.4/preempt-rt"
KHASH ?= "31aebc6b6377591f43c127ccd9230ea01afb0a76"
# Uncomment this to automatically use the newest available commit
#KHASH = "${AUTOREV}"

# Alternative Yocto standard/base kernel repository
# Note: Untested! Provided patches might not cleanly apply
#KREPO = "git://git.yoctoproject.org/linux-yocto;protocol=git"
#KBRANCH = "v5.4/standard/preempt-rt/base"
#KHASH ?= "${AUTOREV}"

KMETA = "kernel-meta"
KMETA_BRANCH = "yocto-5.4"
KMETA_SRC = "git://git.yoctoproject.org/yocto-kernel-cache"
KMETA_HASH ?= "78949176d073f5cf04c9e0c4be699e39528f2880"
# Uncomment this to automatically use the newest available commit
#KMETA_HASH = "${AUTOREV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRCREV_machine_pn-linux-kontron-come-xelx-rt ?= "${KHASH}"
SRCREV_meta_pn-linux-kontron-come-xelx-rt ?= "${KMETA_HASH}"

SRC_URI = "${KREPO};name=machine;branch=${KBRANCH};bareclone=1 \
           ${KMETA_SRC};type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI += "file://kontron-come-xelx-64-preempt-rt.scc \
            file://kontron-come-xelx-64.scc \
            file://kontron-come-xelx.cfg \
           "

PV = "${LINUX_VERSION}+git${SRCPV}"

INCLUDE_PATCHES := "${THISDIR}/linux-kontron-come-xelx/patches-5.4"

include linux-patches.inc

# Add RT-only patches
INCLUDE_RTPATCHES := "${THISDIR}/linux-kontron-come-xelx/patches-5.4_rtonly"
FILESEXTRAPATHS_prepend := "${INCLUDE_RTPATCHES}:"
SRC_URI_append := " ${@__find_patches("${INCLUDE_RTPATCHES}")}"

COMPATIBLE_MACHINE = "kontron-come-xelx-64"

include linux-common.inc
