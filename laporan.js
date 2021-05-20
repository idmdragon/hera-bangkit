const modalWrapper = document.querySelector('.modal-wrapper');
// modal add
const addModal = document.querySelector('.add-modal');
const addModalForm = document.querySelector('.add-modal .form');

// modal edit
const editModal = document.querySelector('.edit-modal');
const editModalForm = document.querySelector('.edit-modal .form');

const btnAdd = document.querySelector('.btn-add');

const tableReport = document.querySelector('.table-reports');

let id;

// Create element and render reports
const renderUser = doc => {
    const tr = `
    <tr data-id='${doc.id}'>
      <td>${doc.data().namaPengirim}</td>
      <td>${doc.data().NIK}</td>
      <td>${doc.data().nomerTelepon}</td>
      <td>${doc.data().tanggalLahir}</td>
      <td>${doc.data().waktuLaporan}</td>
      <td>${doc.data().Alamat}</td>
      <td>${doc.data().Kategori}</td>
      <td>${doc.data().kronologiKejadian}</td>
      <td>
        <button class="btn btn-edit">Edit</button>
        <button class="btn btn-delete">Delete</button>
      </td>
    </tr>
  `;
    tableReport.insertAdjacentHTML('beforeend', tr);

    // Click edit report
    const btnEdit = document.querySelector(`[data-id='${doc.id}'] .btn-edit`);
    btnEdit.addEventListener('click', () => {
        editModal.classList.add('modal-show');

        id = doc.id;
        editModalForm.namaPengirim.value = doc.data().namaPengirim;
        editModalForm.NIK.value = doc.data().NIK;
        editModalForm.nomerTelepon.value = doc.data().nomerTelepon;
        editModalForm.tanggalLahir.value = doc.data().tanggalLahir;
        editModalForm.waktuLaporan.value = doc.data().waktuLaporan;
        editModalForm.Alamat.value = doc.data().Alamat;
        editModalForm.Kategori.value = doc.data().Kategori;
        editModalForm.kronologiKejadian.value = doc.data().kronologiKejadian;
    });

    // Click delete report
    const btnDelete = document.querySelector(`[data-id='${doc.id}'] .btn-delete`);
    btnDelete.addEventListener('click', () => {
        db.collection('laporan').doc(`${doc.id}`).delete().then(() => {
            console.log('Document succesfully deleted!');
        }).catch(err => {
            console.log('Error removing document', err);
        });
    });

}

// Click add report button
btnAdd.addEventListener('click', () => {
    addModal.classList.add('modal-show');

    addModalForm.namaPengirim.value = '';
    addModalForm.NIK.value = '';
    addModalForm.nomerTelepon.value = '';
    addModalForm.tanggalLahir.value = '';
    addModalForm.waktuLaporan.value = '';
    addModalForm.Alamat.value = '';
    addModalForm.Kategori.value = '';
    addModalForm.kronologiKejadian.value = '';
});

// User click anyware outside the modal
window.addEventListener('click', e => {
    if (e.target === addModal) {
        addModal.classList.remove('modal-show');
    }
    if (e.target === editModal) {
        editModal.classList.remove('modal-show');
    }
});

// Get all reports
// db.collection('laporan').get().then(querySnapshot => {
//   querySnapshot.forEach(doc => {
//     renderUser(doc);
//   })
// });

// Real time listener
db.collection('laporan').onSnapshot(snapshot => {
    snapshot.docChanges().forEach(change => {
        if (change.type === 'added') {
            renderUser(change.doc);
        }
        if (change.type === 'removed') {
            let tr = document.querySelector(`[data-id='${change.doc.id}']`);
            let tbody = tr.parentElement;
            tableReport.removeChild(tbody);
        }
        if (change.type === 'modified') {
            let tr = document.querySelector(`[data-id='${change.doc.id}']`);
            let tbody = tr.parentElement;
            tableReport.removeChild(tbody);
            renderUser(change.doc);
        }
    })
})

// Click submit in add modal
addModalForm.addEventListener('submit', e => {
    e.preventDefault();
    db.collection('laporan').add({
        namaPengirim: addModalForm.namaPengirim.value,
        NIK: addModalForm.NIK.value,
        nomerTelepon: addModalForm.nomerTelepon.value,
        tanggalLahir: addModalForm.tanggalLahir.value,
        waktuLaporan: addModalForm.waktuLaporan.value,
        Alamat: addModalForm.Alamat.value,
        Kategori: addModalForm.Kategori.value,
        kronologiKejadian: addModalForm.kronologiKejadian.value,
    });
    modalWrapper.classList.remove('modal-show');
});

// Click submit in edit modal
editModalForm.addEventListener('submit', e => {
    e.preventDefault();
    db.collection('laporan').doc(id).update({
        namaPengirim: editModalForm.namaPengirim.value,
        NIK: editModalForm.NIK.value,
        nomerTelepon: editModalForm.nomerTelepon.value,
        tanggalLahir: editModalForm.tanggalLahir.value,
        waktuLaporan: editModalForm.waktuLaporan.value,
        Alamat: editModalForm.Alamat.value,
        Kategori: editModalForm.Kategori.value,
        kronologiKejadian: editModalForm.kronologiKejadian.value,


    });
    editModal.classList.remove('modal-show');

});