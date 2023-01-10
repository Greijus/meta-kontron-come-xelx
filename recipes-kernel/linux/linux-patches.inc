def __is_patch(fname):
    try:
        return fname[-6:] == '.patch'
    except ValueError:
        return False

def __find_patches(dirname):
    try:
        patches = filter(__is_patch, os.listdir(dirname))
        patches = [ 'file://' + p for p in patches ]
        patches.sort()
        return ' '.join(patches)
    except OSError:
        return ''

PATCHTOOL = "git"
FILESEXTRAPATHS_prepend := "${INCLUDE_PATCHES}:"
SRC_URI_append := " ${@__find_patches("${INCLUDE_PATCHES}")}"
