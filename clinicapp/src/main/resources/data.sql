INSERT INTO accounts (username, password_hash, role, subject_type, subject_document)
VALUES ('admin',  '$2a$10$xOb6WcRWwJ.ktPiDSRdjTuH2vClQFKOq8xt9wMs.JUH9Awq2aYIK2',  'ADMINISTRATIVE', 'EMP', '900000002')
ON DUPLICATE KEY UPDATE
  password_hash   = VALUES(password_hash),
  role            = VALUES(role),
  subject_type    = VALUES(subject_type),
  subject_document= VALUES(subject_document);

INSERT INTO accounts (username, password_hash, role, subject_type, subject_document)
VALUES ('hr1',    '$2a$10$ac9ttAEbMKj9morhoOM7WOJO/DxTWQa0tlkuI2cTwJSOf2MHZRegW',     'HUMAN_RESOURCES','EMP', '900100001')
ON DUPLICATE KEY UPDATE
  password_hash   = VALUES(password_hash),
  role            = VALUES(role),
  subject_type    = VALUES(subject_type),
  subject_document= VALUES(subject_document);

INSERT INTO accounts (username, password_hash, role, subject_type, subject_document)
VALUES ('doc1',   '$2a$10$MQDdr.u1Y6jIS7ZX0/d5lOCTNb4TFcd46JEaz2GHWzuQrgZWEJb.K',    'DOCTOR',         'EMP', '900200001')
ON DUPLICATE KEY UPDATE
  password_hash   = VALUES(password_hash),
  role            = VALUES(role),
  subject_type    = VALUES(subject_type),
  subject_document= VALUES(subject_document);

INSERT INTO accounts (username, password_hash, role, subject_type, subject_document)
VALUES ('enf1', '$2a$10$h0ciYAgLleBlEiBaLw8tK.HSZpe8CwxWFm63RRelVJaTxAOuOwB4G', 'NURSE', 'EMP', '900300001')
ON DUPLICATE KEY UPDATE
  password_hash   = VALUES(password_hash),
  role            = VALUES(role),
  subject_type    = VALUES(subject_type),
  subject_document= VALUES(subject_document);


INSERT INTO accounts (username, password_hash, role, subject_type, subject_document)
VALUES ('sup1',   '$2a$10$w2.8OVttT4thNrCGe5.IWu4ryMC9L6jwcptTgo./wOT8oHuwhzUuK',    'SUPPORT',        'EMP', '900400001')
ON DUPLICATE KEY UPDATE
  password_hash   = VALUES(password_hash),
  role            = VALUES(role),
  subject_type    = VALUES(subject_type),
  subject_document= VALUES(subject_document);
