import React from "react";

const footerStyle = {
    height : '0px',
    backgroundColor: 'black'
}

function Footer() {
  return (
    <div className="footer">
      <footer style = {footerStyle} class="py-4 fixed-bottom">
        <div class="container">
          <p class="m-0 text-center text-white">
            FOR EDUCATIONAL PURPOSES 
          </p>
        </div>
      </footer>
    </div>
  );
}

export default Footer;