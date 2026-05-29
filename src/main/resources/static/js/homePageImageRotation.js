//javascript function to roate home page images on a automated carousel everry 5 secdonds//
// JavaScript function to rotate
// homepage images every 5 secondst

var slideIndex = 0;

showSlides();

function showSlides() {

    const image =
        document.getElementById(
            "carouselImage"
        );

    image.src =
        myImages[slideIndex];

    slideIndex++;

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