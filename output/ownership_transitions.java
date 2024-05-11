// ORM class for table 'ownership_transitions'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Mon Apr 22 17:27:50 MSK 2024
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

public class ownership_transitions extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("from_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ownership_transitions.this.from_address = (String)value;
      }
    });
    setters.put("to_address", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ownership_transitions.this.to_address = (String)value;
      }
    });
    setters.put("num_transitions", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        ownership_transitions.this.num_transitions = (Integer)value;
      }
    });
  }
  public ownership_transitions() {
    init0();
  }
  private String from_address;
  public String get_from_address() {
    return from_address;
  }
  public void set_from_address(String from_address) {
    this.from_address = from_address;
  }
  public ownership_transitions with_from_address(String from_address) {
    this.from_address = from_address;
    return this;
  }
  private String to_address;
  public String get_to_address() {
    return to_address;
  }
  public void set_to_address(String to_address) {
    this.to_address = to_address;
  }
  public ownership_transitions with_to_address(String to_address) {
    this.to_address = to_address;
    return this;
  }
  private Integer num_transitions;
  public Integer get_num_transitions() {
    return num_transitions;
  }
  public void set_num_transitions(Integer num_transitions) {
    this.num_transitions = num_transitions;
  }
  public ownership_transitions with_num_transitions(Integer num_transitions) {
    this.num_transitions = num_transitions;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ownership_transitions)) {
      return false;
    }
    ownership_transitions that = (ownership_transitions) o;
    boolean equal = true;
    equal = equal && (this.from_address == null ? that.from_address == null : this.from_address.equals(that.from_address));
    equal = equal && (this.to_address == null ? that.to_address == null : this.to_address.equals(that.to_address));
    equal = equal && (this.num_transitions == null ? that.num_transitions == null : this.num_transitions.equals(that.num_transitions));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ownership_transitions)) {
      return false;
    }
    ownership_transitions that = (ownership_transitions) o;
    boolean equal = true;
    equal = equal && (this.from_address == null ? that.from_address == null : this.from_address.equals(that.from_address));
    equal = equal && (this.to_address == null ? that.to_address == null : this.to_address.equals(that.to_address));
    equal = equal && (this.num_transitions == null ? that.num_transitions == null : this.num_transitions.equals(that.num_transitions));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.from_address = JdbcWritableBridge.readString(1, __dbResults);
    this.to_address = JdbcWritableBridge.readString(2, __dbResults);
    this.num_transitions = JdbcWritableBridge.readInteger(3, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.from_address = JdbcWritableBridge.readString(1, __dbResults);
    this.to_address = JdbcWritableBridge.readString(2, __dbResults);
    this.num_transitions = JdbcWritableBridge.readInteger(3, __dbResults);
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
    JdbcWritableBridge.writeString(from_address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(to_address, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(num_transitions, 3 + __off, 4, __dbStmt);
    return 3;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(from_address, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(to_address, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(num_transitions, 3 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.from_address = null;
    } else {
    this.from_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.to_address = null;
    } else {
    this.to_address = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.num_transitions = null;
    } else {
    this.num_transitions = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.from_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, from_address);
    }
    if (null == this.to_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, to_address);
    }
    if (null == this.num_transitions) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.num_transitions);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.from_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, from_address);
    }
    if (null == this.to_address) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, to_address);
    }
    if (null == this.num_transitions) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.num_transitions);
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
    __sb.append(FieldFormatter.escapeAndEnclose(from_address==null?"null":from_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(to_address==null?"null":to_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(num_transitions==null?"null":"" + num_transitions, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(from_address==null?"null":from_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(to_address==null?"null":to_address, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(num_transitions==null?"null":"" + num_transitions, delimiters));
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
    if (__cur_str.equals("null")) { this.from_address = null; } else {
      this.from_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.to_address = null; } else {
      this.to_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.num_transitions = null; } else {
      this.num_transitions = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.from_address = null; } else {
      this.from_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.to_address = null; } else {
      this.to_address = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.num_transitions = null; } else {
      this.num_transitions = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    ownership_transitions o = (ownership_transitions) super.clone();
    return o;
  }

  public void clone0(ownership_transitions o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("from_address", this.from_address);
    __sqoop$field_map.put("to_address", this.to_address);
    __sqoop$field_map.put("num_transitions", this.num_transitions);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("from_address", this.from_address);
    __sqoop$field_map.put("to_address", this.to_address);
    __sqoop$field_map.put("num_transitions", this.num_transitions);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
