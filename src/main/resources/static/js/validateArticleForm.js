// Client side Javascript validation for the article form.
// this can also be done with server side validation as per previous examples in the Controllers
// but this provides a better user experience by providing immediate feedback without the need for a page refresh.
// which I did not realize that is why we were doing this for I think the tutor was a bit confused but makes perfect sense now.
function validateArticleForm() {

    const title = document.getElementById("title").value.trim();
    const content = document.getElementById("content").value.trim();

    if (title === "") {
        alert("Article title cannot be blank.");      

        return false;
    }

    if (content.length < 10) {
        alert("Article content must be at least 10 characters.");  

        return false;
    }

    return true;
}

