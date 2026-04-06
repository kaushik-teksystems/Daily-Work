function Modal({ isOpen, title, message, onConfirm, onCancel, type }) {
  if (!isOpen) return null;

  return (
    <div className="modal-overlay">
      <div className="modal-card">
        <h3>{title}</h3>
        <p>{message}</p>
        <div className="modal-actions">
          {type === 'confirm' && (
            <button className="modal-btn cancel" onClick={onCancel}>Cancel</button>
          )}
          <button className="modal-btn confirm" onClick={onConfirm}>
            {type === 'confirm' ? 'Delete' : 'OK'}
          </button>
        </div>
      </div>
    </div>
  );
}

export default Modal;