# Common definitons for all kernel recipes:

DEPENDS += " elfutils elfutils-native"

KERNEL_MODULE_AUTOLOAD_append = " i2c-dev i2c-kempld i2c-mux-kempld kempld_wdt"
KERNEL_MODULE_AUTOLOAD_append = " gpio-kempld nct7802 kontron_bl kontron_eeep"
KERNEL_MODULE_AUTOLOAD_append = " kontron_bootcounter"
KERNEL_MODULE_BLACKLIST = "iTCO_wdt i2c_i801"

FILES_${KERNEL_PACKAGE_NAME}-modules_append = " ${sysconfdir}/modprobe.d/*"

# Blacklist drivers listed in KERNEL_MODULE_BLACKLIST
do_install_append() {
    echo -n > ${WORKDIR}/blacklist.conf
    for MODULE in ${KERNEL_MODULE_BLACKLIST} ; do
        echo "blacklist ${MODULE}" >> ${WORKDIR}/blacklist.conf
    done
    install -d ${D}${sysconfdir}/modprobe.d
    install -m 0644 ${WORKDIR}/blacklist.conf ${D}${sysconfdir}/modprobe.d/
}

# If this kernel is for a development image (like kontron-test-image) enable
# some kernel debug features
KERNEL_FEATURES_append = " ${@bb.utils.contains('IMAGE_FEATURES', \
        'debug-tweaks', 'debug-features.scc', '', d)}"
