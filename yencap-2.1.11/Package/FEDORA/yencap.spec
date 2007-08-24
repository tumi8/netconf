Summary: Netconf agent (Network device management)
Name: yencap
Version: 1.1.25
Release: 1
License: LGPL
Group: Applications
URL: www.madynes.org
Requires: python >= 2.4.1, python-devel >= 2.4.1, xmlsec1 >= 1.2.7, xmlsec1-devel >= 1.2.7, xmlsec1-openssl >= 1.2.7, xmlsec1-openssl-devel >= 1.2.7, libxml2 >= 2.6.16, libxml2-devel >= 2.6.20, libxml2-python >= 2.6.20, 4Suite >= 1.0, PyXML >= 0.8.4, python-crypto >= 2.0.1
BuildRoot:     %{_tmppath}/%{name}-root
Source0: %{name}-%{version}.tar.gz

%description
YencaP is an implementation of the agent side of Netconf. It supports new capabilities and new data models.
It is developed by the Madynes Research team (www.madyne.org).
http://libresource.inria.fr/projects/ensuite

%prep
%setup -q -n %{name}-%{version}

%build

%install

[ "${RPM_BUILD_ROOT}" != "/" ] && rm -rf ${RPM_BUILD_ROOT}
mkdir -p $RPM_BUILD_ROOT/%{_datadir}/%{name}-%{version}
tar -cvSpf- . | (cd $RPM_BUILD_ROOT/%{_datadir}/%{name}-%{version} ; tar -xSpf-)

%clean
[ "${RPM_BUILD_ROOT}" !=  "/" ] && rm -rf ${RPM_BUILD_ROOT}

%files
%defattr(-,root,root)
%{_datadir}/%{name}-%{version}/*

%changelog
* Thu Apr 14 2005 vincent CRIDLIG <vincent.cridlig@loria.fr> - 
- Initial build.

