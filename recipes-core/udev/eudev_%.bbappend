# Add kvm group to prevent errors if kvm device is created
inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "-g 135 kvm"
