import React from "react";

const MessageAdvertencia = ({ msg, bgColor }) => {
  let styles = {
    padding: '0.33rem',
    marginBottom: "1rem",
    textAlign: "center",
    color: "#000",
    fontWeight: "bold",
    borderRadius: "50px",
    backgroundColor: bgColor,
  };

  return (
    <div style={styles}>
      {/* <p>{msg}</p> */}
      <p dangerouslySetInnerHTML={{ __html: msg }} />
    </div>
  );
};

export default MessageAdvertencia;