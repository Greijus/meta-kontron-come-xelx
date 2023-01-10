# arch/x86/purgatory/string.c has been removed in recent kernel versions.
# To keep kernel-devsrc install work till this is fixed upstream, we create an
# empty file that can be installed and remove it later.

do_install_append() {
    if [ ! -s "$kerneldir/build/string.c" ]; then
        rm -f $kerneldir/build/string.c
        rm -f ${S}/arch/x86/purgatory/string.c
    fi
}

do_install_prepend() {
    if [ ! -f "${S}/arch/x86/purgatory/string.c" ]; then
        touch ${S}/arch/x86/purgatory/string.c
    fi
}
