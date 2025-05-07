(() => {

    let closedPopupDetailForm = (popupForm) => {
        let closeButton = popupForm.querySelector('.close-button');
        closeButton.addEventListener('click', function() {
            popupForm.style.display = 'none';
            let content = popupForm.querySelector('.popup-form-content');
            content.innerHTML = ``;
        })
    }

    let searchButton = document.querySelector('.filter-button');

    searchButton.addEventListener('click', function(event) {
        event.preventDefault();
        let popup = document.querySelector('.popup-form');
        let nomorKtp = document.querySelector('input[name="nomorKtp"]').value;
        let request = new XMLHttpRequest();
        let url = `http://localhost:8080/nasabah-api/ktp-search?nomorKtp=${nomorKtp}`
        request.open("GET", url);
        request.send();
        request.onload = () => {
            if(request.status == 200){
                let response = JSON.parse(request.response);
                let data = response.data;

                popup.style.display = 'block';

                let content = document.querySelector('.popup-form-content');
                let nasabahGrid = '';

                data.forEach(nas => {
                    nasabahGrid = nasabahGrid + `
                        <tr>
                            <td>${nas.nomorRekening}</td>
                            <td>${nas.namaLengkap}</td>
                            <td>${nas.nomorKtp}</td>
                            <td>${nas.tanggalDaftar}</td>
                            <td>
                                <a class="blue-button detail-button" href="" no-rekening=${nas.nomorRekening}>
                                    <i class="fa fa-info-circle" aria-hidden="true"></i>
                                </a>
                                <a class="blue-button update-button" no-rekening=${nas.nomorRekening}>
                                    <i class="fas fa-edit" aria-hidden="true"></i>
                                </a>
                                <a class="blue-button delete-button" no-rekening=${nas.nomorRekening}>
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </td>
                        </tr>
                        `
                });

                content.innerHTML = `
                    <button class="close-button">x</button>
                    <div class="menu-popup">
                        <h3> KTP ${nomorKtp} </h3>
                    </div>
                    <table class="index-table">
                      <thead>
                          <th>Nomor Rekening</th>
                          <th>Nama Lengkap</th>
                          <th>Nomor KTP</th>
                          <th>Tanggal Daftar</th>
                          <th>Action</th>
                      </thead>
                      <tbody>
                        ${nasabahGrid}
                      </tbody>
                      <tfoot>
                          <tr>
                            <td colspan="5">
                            </td>
                          </tr>
                      </tfoot>
                    </table>
                `
                closedPopupDetailForm(popup);
                detailFunction();
                updateFunction();
                deleteFunction();
            }
        }
    });

    let detailFunction = () => {
        let detailButton = document.querySelectorAll('.detail-button');

        detailButton.forEach(function(button) {
            button.addEventListener('click', function(event) {
                event.preventDefault();
                let popup = document.querySelector('.popup-form');
                let noRek = button.getAttribute('no-rekening');
                let request = new XMLHttpRequest();
                let url = `http://localhost:8080/nasabah-api/detail?nomorRekening=${noRek}`
                request.open("GET", url);
                request.send();
                request.onload = () => {
                    if(request.status == 200){
                        let response = JSON.parse(request.response);
                        let data = response.data;

                        let nomorRekening = data.nomorRekening;
                        let nomorKtp = data.nomorKtp;
                        let namaLengkap = data.namaLengkap;
                        let nomorHp = data.nomorHp;
                        let alamat = data.alamat;
                        let tempatLahir = data.tempatLahir;
                        let tanggalLahir = data.tanggalLahir;
                        let tanggalDaftar = data.tanggalDaftar;

                        popup.style.display = 'block';

                        let content = document.querySelector('.popup-form-content');
                        content.innerHTML = `
                            <button class="close-button">x</button>
                             <div class="menu-popup">
                                 <h3> Nasabah Detail </h3>
                             </div>
                            <table id="detail-prospect">
                                <tbody>
                                <tr>
                                    <td><label>Nomor Rekening</label></td>
                                    <td>
                                        <span>${nomorRekening}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Nomor KTP</label></td>
                                    <td>
                                        <span>${nomorKtp}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Nama Lengkap</label></td>
                                    <td>
                                        <span>${namaLengkap}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Nomor HP</label></td>
                                    <td>
                                        <span>${nomorHp}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Alamat</label></td>
                                    <td>
                                        <span>${alamat}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Tempat Lahir</label></td>
                                    <td>
                                        <span>${tempatLahir}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Tanggal Lahir</label></td>
                                    <td>
                                        <span>${tanggalLahir}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Tanggal Daftar</label></td>
                                    <td>
                                        <span>${tanggalDaftar}</span>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                         `
                        closedPopupDetailForm(popup);
                    }
                }
            });
        });
    }

    let updateFunction = () => {
        let updateButtons = document.querySelectorAll('.update-button');

        updateButtons.forEach(function(button) {
            button.addEventListener('click', function(event) {
                event.preventDefault();
                let noRek = button.getAttribute('no-rekening');

                let request = new XMLHttpRequest();
                let url = `http://localhost:8080/nasabah-api/detail?nomorRekening=${noRek}`
                request.open("GET", url);
                request.send();
                request.onload = () => {
                    if (request.status == 200){
                        let response = JSON.parse(request.response);
                        let data = response.data;

                        let namaLengkap = data.namaLengkap;
                        let nomorKtp = data.nomorKtp;
                        let nomorHp = data.nomorHp;
                        let alamat = data.alamat;

                        let popup = document.querySelector('.popup-form');
                        popup.style.display = 'block';

                        let content = document.querySelector('.popup-form-content');
                        content.innerHTML = `
                             <button class="close-button">x</button>
                             <div class="menu-popup">
                                 <h3> Update Data Nasabah </h3>
                             </div>
                             <form class="save-form">
                                 <table class="assign-new">
                                     <tbody>
                                     <tr>
                                         <td><label>Nomor Rekening*</label></td>
                                         <td>
                                             <input name="nomorRekening" type="text" value="${noRek}" readonly>
                                             <div class="field-validation-error" id="error-nomorRekening"></div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td><label>Nama Lengkap*</label></td>
                                         <td>
                                             <input name="namaLengkap" type="text" value="${namaLengkap}" readonly>
                                             <div class="field-validation-error" id="error-namaLengkap"></div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td><label>Nomor KTP*</label></td>
                                         <td>
                                             <input name="nomorKtp" type="text" value="${nomorKtp}" readonly>
                                             <div class="field-validation-error" id="error-nomorKtp"></div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td><label>Nomor HP*</label></td>
                                         <td>
                                             <input name="nomorHp" type="text" value="${nomorHp}">
                                             <div class="field-validation-error" id="error-nomorHp"></div>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td><label>Alamat*</label></td>
                                         <td>
                                             <input name="alamat" type="text" value="${alamat}">
                                             <div class="field-validation-error" id="error-alamat"></div>
                                         </td>
                                     </tr>
                                     </tbody>
                                 </table>
                                 <button class="blue-button save-button" type="submit" id="save-new">
                                    Save
                                    <i class="fas fa-paper-plane" aria-hidden="true"></i>
                                 </button>
                             </form>
                        `
                        saveUpdateNasabah();
                        closedPopupDetailForm(popup);
                    }
                }
            });
        });
    }

    let deleteFunction = () => {
        let deleteButtons = document.querySelectorAll('.delete-button');

        deleteButtons.forEach(function(button) {
            button.addEventListener('click', function(event) {
                event.preventDefault();
                let noRek = button.getAttribute('no-rekening');
        //            let shipmentName = button.getAttribute('shipmentName');
                let request = new XMLHttpRequest();
                request.open('DELETE', `http://localhost:8080/nasabah-api/delete?nomorRekening=${noRek}`, true);
                request.setRequestHeader('Content-Type', 'application/json');
                request.send();
                request.onload = function() {
                    if (request.status == 200) {
                        console.log('Data berhasil dihapus:', request.responseText);
                        window.location.href = 'http://localhost:8080/';
                    } else {
                        let popup = document.querySelector('.popup-form');
                        popup.style.display = 'block';

                        let content = document.querySelector('.popup-form-content');
                        content.innerHTML = `
                         <button class="close-button">x</button>
                         <span>
                            This Prospect cannot be deleted because it already Nasabah
                         </span>
                         `
                        closedPopupDetailForm(popup);
                    }
                };

            });
        });

    }

    let insertButton = document.querySelector('.create-button');

    insertButton.addEventListener('click', function(event) {
        event.preventDefault();

        let popup = document.querySelector('.popup-form');
        popup.style.display = 'block';

        let content = document.querySelector('.popup-form-content');
        content.innerHTML = `
         <button class="close-button">x</button>
         <div class="menu-popup">
             <h3> Daftar Nasabah Baru </h3>
         </div>
         <form class="save-form">
             <table class="assign-new">
                 <tbody>
                 <tr>
                     <td><label>Nomor KTP*</label></td>
                     <td>
                         <input name="nomorKtp" type="text">
                         <div class="field-validation-error" id="error-nomorKtp"></div>
                     </td>
                 </tr>
                 <tr>
                     <td><label>Nama Lengkap*</label></td>
                     <td>
                         <input name="namaLengkap" type="text">
                         <div class="field-validation-error" id="error-namaLengkap"></div>
                     </td>
                 </tr>
                 <tr>
                     <td><label>Nomor HP*</label></td>
                     <td>
                         <input name="nomorHp" type="text">
                         <div class="field-validation-error" id="error-nomorHp"></div>
                     </td>
                 </tr>
                 <tr>
                     <td><label>Tempat Lahir*</label></td>
                     <td>
                         <input name="tempatLahir" type="text">
                         <div class="field-validation-error" id="error-tempatLahir"></div>
                     </td>
                 </tr>
                <tr>
                    <td><label for="tanggalLahir">Tanggal Lahir*</label></td>
                    <td>
                        <input name="tanggalLahir" id="tanggalLahir" type="date" required>
                        <div class="field-validation-error" id="error-tanggalLahir"></div>
                    </td>
                </tr>
                <tr>
                    <td><label>Alamat*</label></td>
                    <td>
                         <input name="alamat" type="text">
                         <div class="field-validation-error" id="error-alamat"></div>
                    </td>
                </tr>
                </tbody>
             </table>
             <button class="blue-button save-button" type="submit" id="save-new">
                Save
                <i class="fas fa-paper-plane" aria-hidden="true"></i>
             </button>
         </form>
         `
        saveNewNasabah();
        closedPopupDetailForm(popup);
    });

    let saveNewNasabah = () => {
        console.log("Kesini")
        let saveButton = document.querySelector('#save-new');
        saveButton.addEventListener('click', function(event) {
            event.preventDefault();
            let content = document.querySelector('.popup-form-content');

            let nomorKtp = content.querySelector('input[name="nomorKtp"]').value;
            let namaLengkap = content.querySelector('input[name="namaLengkap"]').value;
            let nomorHp = content.querySelector('input[name="nomorHp"]').value;
            let tempatLahir = content.querySelector('input[name="tempatLahir"]').value;
            let tanggalLahir = content.querySelector('input[name="tanggalLahir"]').value;
            let alamat = content.querySelector('input[name="alamat"]').value;

            let formData = {
                nomorKtp: nomorKtp,
                namaLengkap: namaLengkap,
                nomorHp: nomorHp,
                alamat: alamat,
                tempatLahir: tempatLahir,
                tanggalLahir: tanggalLahir
            };

            console.log(formData);

            let form = JSON.stringify(formData);
            let request = new XMLHttpRequest();
            request.open('POST', 'http://localhost:8080/nasabah-api/save-new');
            request.setRequestHeader('Content-Type', 'application/json');
//            request.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("apiToken"));
//            console.log(localStorage.getItem("apiToken"));
            request.send(form);
            request.onload = () => {
                if (request.status == 200) {
                    console.log(request.response);
                    console.log('Data berhasil diupdate:', request.responseText);
                    window.location.href = 'http://localhost:8080/';
                } else if (request.status == 422) {
//                    console.log(JSON.parse(request.response));
//                    let response = JSON.parse(request.response);
//                    responseError(response);
                } else {
                    let popup = document.querySelector('.popup-form');
                    popup.style.display = 'block';

                    let content = document.querySelector('.popup-form-content');
                    content.innerHTML = `
                     <button class="close-button">x</button>
                     <span>
                        Data tidak lengkap, silahkan isi form ulang!
                     </span>
                     `
                    closedPopupDetailForm(popup);
                }
            }
        });
    }

    let saveUpdateNasabah = () => {
        console.log("Kesini")
        let saveButton = document.querySelector('#save-new');
        saveButton.addEventListener('click', function(event) {
            event.preventDefault();
            let content = document.querySelector('.popup-form-content');

            let nomorRekening = content.querySelector('input[name="nomorRekening"]').value;
            let nomorHp = content.querySelector('input[name="nomorHp"]').value;
            let alamat = content.querySelector('input[name="alamat"]').value;

            let formData = {
                nomorRekening: nomorRekening,
                nomorHp: nomorHp,
                alamat: alamat
            };

            console.log(formData);

            let form = JSON.stringify(formData);
            let request = new XMLHttpRequest();
            request.open('POST', 'http://localhost:8080/nasabah-api/save-update');
            request.setRequestHeader('Content-Type', 'application/json');
//            request.setRequestHeader("Authorization", "Bearer " + localStorage.getItem("apiToken"));
//            console.log(localStorage.getItem("apiToken"));
            request.send(form);
            request.onload = () => {
                if (request.status == 200) {
                    console.log(request.response);
                    console.log('Data berhasil diupdate:', request.responseText);
                    window.location.href = 'http://localhost:8080/';
                } else if (request.status == 422) {
//                    console.log(JSON.parse(request.response));
//                    let response = JSON.parse(request.response);
//                    responseError(response);
                } else {
                    window.location.href = 'http://localhost:8080/home';
                }
            }
        });
    }


})()
