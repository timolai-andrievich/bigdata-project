// ORM class for table 'transfer_statistics_by_address'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Apr 22 17:26:33 MSK 2024
// For connector: org.apache.sqoop.manager.PostgresqlManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import org.apache.sqoop.lib.JdbcWritableBridge;
import org.apache.sqoop.lib.DelimiterSet;
import org.apache.sqoop.lib.FieldFormatter;
import org.apache.sqoop.lib.RecordParser;
import org.apache.sqoop.lib.BooleanParser;
import org.apache.sqoop.lib.BlobRef;
import org.apache.sqoop.lib.ClobRef;
import org.apache.sqoop.lib.LargeObjectLoader;
import org.apache.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class transfer_statistics_by_address extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_statistics_by_address.this.address = (String)value;
      }
    });
    setters.put("transfers_out", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_statistics_by_address.this.transfers_out = (Integer)value;
      }
    });
    setters.put("transfers_in", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transfer_statistics_by_address.this.transfers_in = (Integer)value;
      }
    });
  }
  public transfer_statistics_by_address() {
    init0();
  }
  private String address;
  public String get_address() {
    return address;
  }
  public void set_address(String address) {
    this.address = address;
  }
  public transfer_statistics_by_address with_address(String address) {
    this.address = address;
    return this;
  }
  private Integer transfers_out;
  public Integer get_transfers_out() {
    return transfers_out;
  }
  public void set_transfers_out(Integer transfers_out) {
    this.transfers_out = transfers_out;
  }
  public transfer_statistics_by_address with_transfers_out(Integer transfers_out) {
    this.transfers_out = transfers_out;
    return this;
  }
  private Integer transfers_in;
  public Integer get_transfers_in() {
    return transfers_in;
  }
  public void set_transfers_in(Integer transfers_in) {
    this.transfers_in = transfers_in;
  }
  public transfer_statistics_by_address with_transfers_in(Integer transfers_in) {
    this.transfers_in = transfers_in;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transfer_statistics_by_address)) {
      return false;
    }
    transfer_statistics_by_address that = (transfer_statistics_by_address) o;
    boolean equal = true;
    equal = equal && (this.address == null ? that.address == null : this.address.equals(that.address));
    equal = equal && (this.transfers_out == null ? that.transfers_out == null : this.transfers_out.equals(that.transfers_out));
    equal = equal && (this.transfers_in == null ? that.transfers_in == null : this.transfers_in.equals(that.transfers_in));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transfer_statistics_by_address)) {
      return false;
    }
    transfer_statistics_by_address that = (transfer_statistics_by_address) o;
    boolean equal = true;
    equal = equal && (this.address == null ? that.address == null : this.address.equals(that.address));
    equal = equal && (this.transfers_out == null ? that.transfers_out == null : this.transfers_out.equals(that.transfers_out));
    equal = equal && (this.transfers_in == null ? that.transfers_in == null : this.transfers_in.equals(that.transfers_in));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.address = JdbcWritableBridge.readString(1, __dbResults);
    this.transfers_out = JdbcWritableBridge.readInteger(2, __dbResults);
    this.transfers_in = JdbcWritableBridge.readInteger(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.address = JdbcWritableBridge.readString(1, __dbResults);
    this.transfers_out = JdbcWritableBridge.readInteger(2, __dbResults);
    this.transfers_in = JdbcWritableBridge.readInteger(3, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(transfers_out, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(transfers_in, 3 + __off, 4, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(transfers_out, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(transfers_in, 3 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.address = null;
    } else {
    this.address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.transfers_out = null;
    } else {
    this.transfers_out = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.transfers_in = null;
    } else {
    this.transfers_in = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, address);
    }
    if (null == this.transfers_out) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transfers_out);
    }
    if (null == this.transfers_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transfers_in);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, address);
    }
    if (null == this.transfers_out) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transfers_out);
    }
    if (null == this.transfers_in) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transfers_in);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(address==null?"null":address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transfers_out==null?"null":"" + transfers_out, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transfers_in==null?"null":"" + transfers_in, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(address==null?"null":address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transfers_out==null?"null":"" + transfers_out, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transfers_in==null?"null":"" + transfers_in, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.address = null; } else {
      this.address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transfers_out = null; } else {
      this.transfers_out = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transfers_in = null; } else {
      this.transfers_in = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.address = null; } else {
      this.address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transfers_out = null; } else {
      this.transfers_out = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transfers_in = null; } else {
      this.transfers_in = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    transfer_statistics_by_address o = (transfer_statistics_by_address) super.clone();
    return o;
  }

  public void clone0(transfer_statistics_by_address o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("address", this.address);
    __sqoop$field_map.put("transfers_out", this.transfers_out);
    __sqoop$field_map.put("transfers_in", this.transfers_in);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("address", this.address);
    __sqoop$field_map.put("transfers_out", this.transfers_out);
    __sqoop$field_map.put("transfers_in", this.transfers_in);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
