Sleep(2000)
ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1",@WorkingDir&"\resource\ReleaseNotes.docx")
ControlClick("Open","","Button1")