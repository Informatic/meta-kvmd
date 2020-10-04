do_install_append() {
	# Disable default nginx when running kvmd
	rm ${D}${systemd_unitdir}/system/nginx.service
	rm -rf ${D}${systemd_unitdir}
	rmdir ${D}/lib
}

SYSTEMD_SERVICE_${PN}_remove = " nginx.service "
