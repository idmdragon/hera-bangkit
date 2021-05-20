var firebaseConfig = {
    apiKey: "AIzaSyDgXAHnBMIGF75Eo3IzL16VYhkcOoJ_H2g",
    authDomain: "process.env.authDomain",
    projectId: "process.env.projectId",
    storageBucket: "process.env.storageBucket",
    messagingSenderId: "process.env.messagingSenderId",
    appId: "process.env.appId"


};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();