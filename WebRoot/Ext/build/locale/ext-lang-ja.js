/*
 * Japanese translation
 * By tyama
 * 04-08-2007, 05:49 AM
 */

Ext.UpdateManager.defaults.indicatorText = '<div class="loading-indicator">�i���z����...</div>';

if(Ext.View){
  Ext.View.prototype.emptyText = "";
}

if(Ext.grid.GridPanel){
  Ext.grid.GridPanel.prototype.ddText = "{0} ���x�k";
}

if(Ext.TabPanelItem){
  Ext.TabPanelItem.prototype.closeText = "���Υ��֤��]����";
}

if(Ext.form.Field){
  Ext.form.Field.prototype.invalidText = "�ե��`��ɤ΂��������Ǥ���";
}

if(Ext.LoadMask){
    Ext.LoadMask.prototype.msg = "�i���z����...";
}

Date.monthNames = ['1��', '2��', '3��', '4��', '5��', '6��', '7��', '8��', '9��', '10��','11��','12��'];

Date.dayNames = [
 "��",
 "��",
 "��",
 "ˮ",
 "ľ",
 "��",
 "��"];

if(Ext.MessageBox){
  Ext.MessageBox.buttonText = {
    ok : "OK",
    cancel : "����󥻥�",
    yes : "�Ϥ�",
    no : "������"
  };
}

if(Ext.util.Format){
  Ext.util.Format.date = function(v, format){
     if(!v) return "";
     if(!(v instanceof Date)) v = new Date(Date.parse(v));
     return v.dateFormat(format || "Y/m/d");
  };
}

if(Ext.DatePicker){
  Ext.apply(Ext.DatePicker.prototype, {
     todayText         : "����",
     minText           : "�x�k�����ո�����С�����¤Ǥ���",
     maxText           : "�x�k�����ո���������ϤǤ���",
     disabledDaysText  : "",
     disabledDatesText : "",
     monthNames	       : Date.monthNames,
     dayNames	       : Date.dayNames,
     nextText          : '���¤� (����ȥ�`��+��)',
     prevText          : 'ǰ�¤� (����ȥ�`��+��)',
     monthYearText     : '���x�k (����ȥ�`��+��/�¤����Ƅ�)',
     todayTip          : "{0} (���ک`�����`)",
     format            : "Y/m/d"
  });
}

if(Ext.PagingToolbar){
  Ext.apply(Ext.PagingToolbar.prototype, {
     beforePageText : "�ک`��",
     afterPageText  : "/ {0}",
     firstText      : "����Υک`��",
     prevText       : "ǰ�Υک`��",
     nextText       : "�ΤΥک`��",
     lastText       : "����Υک`��",
     refreshText    : "����",
     displayMsg     : "{2} ���� {0} - {1} ���ʾ",
     emptyMsg       : '��ʾ����ǩ`��������ޤ���'
  });
}

if(Ext.form.TextField){
  Ext.apply(Ext.form.TextField.prototype, {
     minLengthText : "���Υե��`��ɤ���С���� {0} �Ǥ���",
     maxLengthText : "���Υե��`��ɤ���󂎤� {0} �Ǥ���",
     blankText     : "����Ŀ�Ǥ���",
     regexText     : "",
     emptyText     : null
  });
}

if(Ext.form.NumberField){
  Ext.apply(Ext.form.NumberField.prototype, {
     minText : "���Υե��`��ɤ���С���� {0} �Ǥ���",
     maxText : "���Υե��`��ɤ���󂎤� {0} �Ǥ���",
     nanText : "{0} �������ǤϤ���ޤ���"
  });
}

if(Ext.form.DateField){
  Ext.apply(Ext.form.DateField.prototype, {
     disabledDaysText  : "�o��",
     disabledDatesText : "�o��",
     minText           : "���Υե��`��ɤ��ո��ϡ� {0} �Խ����ո����O�����Ƥ���������",
     maxText           : "���Υե��`��ɤ��ո��ϡ� {0} ��ǰ���ո����O�����Ƥ���������",
     invalidText       : "{0} ���g�`�ä��ո������Ǥ��� - ������ʽ�ϡ�{1}���Ǥ���",
     format            : "Y/m/d"
  });
}

if(Ext.form.ComboBox){
  Ext.apply(Ext.form.ComboBox.prototype, {
     loadingText       : "�i���z����...",
     valueNotFoundText : undefined
  });
}

if(Ext.form.VTypes){
  Ext.apply(Ext.form.VTypes, {
     emailText    : '��`�륢�ɥ쥹��"user@domain.com"����ʽ���������Ƥ���������',
     urlText      : 'URL��"http:/'+'/www.domain.com"����ʽ���������Ƥ���������',
     alphaText    : '���Ӣ�֤�"_"�ΤߤǤ���',
     alphanumText : '���Ӣ����"_"�ΤߤǤ���'
  });
}

if(Ext.grid.GridView){
  Ext.apply(Ext.grid.GridView.prototype, {
     sortAscText  : "�N�",
     sortDescText : "���",
     lockText     : "������å�",
     unlockText   : "������å����",
     columnsText  : "Columns"
  });
}

if(Ext.grid.PropertyColumnModel){
  Ext.apply(Ext.grid.PropertyColumnModel.prototype, {
     nameText   : "����",
     valueText  : "��",
     dateFormat : "Y/m/d"
  });
}

if(Ext.layout.BorderLayout && Ext.layout.BorderLayout.SplitRegion){
  Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype, {
     splitTip            : "�ɥ�å�����ȥꥵ�����Ǥ��ޤ���",
     collapsibleSplitTip : "�ɥ�å��ǥꥵ������ ���֥륯��å����L����"
  });
}
