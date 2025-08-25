function validateForm() {
    const email = document.getElementById("Email").value.trim();
    const password = document.getElementById("Password").value.trim();

    // if email has value and password not
    if ( ( email !== "" && password === "") || (email === "" && password !== "") ) {
      alert("please Chack your Email and Password");
      return false; 
    }

    return true; 
 }
  
function togglePassword() {
    const passInput = document.getElementById("Password");
    if (passInput.type === "password") {
      passInput.type = "text";  // Show password
    } else {
      passInput.type = "password"; // Hide password
    }
 }