//javascript function to roate home page images on a automated carousel everry 5 secdonds//
// JavaScript function to rotate
// homepage images every 5 secondst

var slideIndex = 0;

function showSlides() {
    const image = document.getElementById( "carouselImage");

    image.src =
        myImages[slideIndex];// cleveerly this is made in anticipation for the image array that gets loaded in the controller stage

    slideIndex++;// increment slider for the index of the image array

    if (
        slideIndex >=
        myImages.length
        ) {
            slideIndex = 0;
        }
    setTimeout(
        showSlides,
        5000
    );   
}
 showSlides();// calling the function to start the carousel