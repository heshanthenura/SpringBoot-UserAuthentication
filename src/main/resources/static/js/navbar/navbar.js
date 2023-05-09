const menuBtn = document.getElementById("menuBtn")
const mobileMenu = document.getElementById("mobileMenu")

menuBtn.onclick = () =>{
    if (mobileMenu.style.display == "none"){mobileMenu.style.display="flex"}else{mobileMenu.style.display="none"}
}