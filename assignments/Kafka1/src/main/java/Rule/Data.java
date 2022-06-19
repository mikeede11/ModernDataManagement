/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package Rule;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Data extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5518913089251745293L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Data\",\"namespace\":\"Rule\",\"fields\":[{\"name\":\"sensor_id\",\"type\":\"long\"},{\"name\":\"time\",\"type\":\"long\"},{\"name\":\"status\",\"type\":[\"null\",{\"type\":\"enum\",\"name\":\"Alert\",\"symbols\":[\"CRITICAL\",\"MINOR\"]}]}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Data> ENCODER =
      new BinaryMessageEncoder<Data>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Data> DECODER =
      new BinaryMessageDecoder<Data>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Data> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Data> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Data> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<Data>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Data to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Data from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Data instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Data fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private long sensor_id;
  private long time;
  private Rule.Alert status;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Data() {}

  /**
   * All-args constructor.
   * @param sensor_id The new value for sensor_id
   * @param time The new value for time
   * @param status The new value for status
   */
  public Data(java.lang.Long sensor_id, java.lang.Long time, Rule.Alert status) {
    this.sensor_id = sensor_id;
    this.time = time;
    this.status = status;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sensor_id;
    case 1: return time;
    case 2: return status;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sensor_id = (java.lang.Long)value$; break;
    case 1: time = (java.lang.Long)value$; break;
    case 2: status = (Rule.Alert)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'sensor_id' field.
   * @return The value of the 'sensor_id' field.
   */
  public long getSensorId() {
    return sensor_id;
  }


  /**
   * Sets the value of the 'sensor_id' field.
   * @param value the value to set.
   */
  public void setSensorId(long value) {
    this.sensor_id = value;
  }

  /**
   * Gets the value of the 'time' field.
   * @return The value of the 'time' field.
   */
  public long getTime() {
    return time;
  }


  /**
   * Sets the value of the 'time' field.
   * @param value the value to set.
   */
  public void setTime(long value) {
    this.time = value;
  }

  /**
   * Gets the value of the 'status' field.
   * @return The value of the 'status' field.
   */
  public Rule.Alert getStatus() {
    return status;
  }


  /**
   * Sets the value of the 'status' field.
   * @param value the value to set.
   */
  public void setStatus(Rule.Alert value) {
    this.status = value;
  }

  /**
   * Creates a new Data RecordBuilder.
   * @return A new Data RecordBuilder
   */
  public static Rule.Data.Builder newBuilder() {
    return new Rule.Data.Builder();
  }

  /**
   * Creates a new Data RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Data RecordBuilder
   */
  public static Rule.Data.Builder newBuilder(Rule.Data.Builder other) {
    if (other == null) {
      return new Rule.Data.Builder();
    } else {
      return new Rule.Data.Builder(other);
    }
  }

  /**
   * Creates a new Data RecordBuilder by copying an existing Data instance.
   * @param other The existing instance to copy.
   * @return A new Data RecordBuilder
   */
  public static Rule.Data.Builder newBuilder(Rule.Data other) {
    if (other == null) {
      return new Rule.Data.Builder();
    } else {
      return new Rule.Data.Builder(other);
    }
  }

  /**
   * RecordBuilder for Data instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Data>
    implements org.apache.avro.data.RecordBuilder<Data> {

    private long sensor_id;
    private long time;
    private Rule.Alert status;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(Rule.Data.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sensor_id)) {
        this.sensor_id = data().deepCopy(fields()[0].schema(), other.sensor_id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.time)) {
        this.time = data().deepCopy(fields()[1].schema(), other.time);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Data instance
     * @param other The existing instance to copy.
     */
    private Builder(Rule.Data other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.sensor_id)) {
        this.sensor_id = data().deepCopy(fields()[0].schema(), other.sensor_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.time)) {
        this.time = data().deepCopy(fields()[1].schema(), other.time);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.status)) {
        this.status = data().deepCopy(fields()[2].schema(), other.status);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'sensor_id' field.
      * @return The value.
      */
    public long getSensorId() {
      return sensor_id;
    }


    /**
      * Sets the value of the 'sensor_id' field.
      * @param value The value of 'sensor_id'.
      * @return This builder.
      */
    public Rule.Data.Builder setSensorId(long value) {
      validate(fields()[0], value);
      this.sensor_id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'sensor_id' field has been set.
      * @return True if the 'sensor_id' field has been set, false otherwise.
      */
    public boolean hasSensorId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'sensor_id' field.
      * @return This builder.
      */
    public Rule.Data.Builder clearSensorId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'time' field.
      * @return The value.
      */
    public long getTime() {
      return time;
    }


    /**
      * Sets the value of the 'time' field.
      * @param value The value of 'time'.
      * @return This builder.
      */
    public Rule.Data.Builder setTime(long value) {
      validate(fields()[1], value);
      this.time = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'time' field has been set.
      * @return True if the 'time' field has been set, false otherwise.
      */
    public boolean hasTime() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'time' field.
      * @return This builder.
      */
    public Rule.Data.Builder clearTime() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'status' field.
      * @return The value.
      */
    public Rule.Alert getStatus() {
      return status;
    }


    /**
      * Sets the value of the 'status' field.
      * @param value The value of 'status'.
      * @return This builder.
      */
    public Rule.Data.Builder setStatus(Rule.Alert value) {
      validate(fields()[2], value);
      this.status = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'status' field has been set.
      * @return True if the 'status' field has been set, false otherwise.
      */
    public boolean hasStatus() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'status' field.
      * @return This builder.
      */
    public Rule.Data.Builder clearStatus() {
      status = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Data build() {
      try {
        Data record = new Data();
        record.sensor_id = fieldSetFlags()[0] ? this.sensor_id : (java.lang.Long) defaultValue(fields()[0]);
        record.time = fieldSetFlags()[1] ? this.time : (java.lang.Long) defaultValue(fields()[1]);
        record.status = fieldSetFlags()[2] ? this.status : (Rule.Alert) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Data>
    WRITER$ = (org.apache.avro.io.DatumWriter<Data>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Data>
    READER$ = (org.apache.avro.io.DatumReader<Data>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.sensor_id);

    out.writeLong(this.time);

    if (this.status == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeEnum(this.status.ordinal());
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.sensor_id = in.readLong();

      this.time = in.readLong();

      if (in.readIndex() != 1) {
        in.readNull();
        this.status = null;
      } else {
        this.status = Rule.Alert.values()[in.readEnum()];
      }

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.sensor_id = in.readLong();
          break;

        case 1:
          this.time = in.readLong();
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.status = null;
          } else {
            this.status = Rule.Alert.values()[in.readEnum()];
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










