/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package Rule;
@org.apache.avro.specific.AvroGenerated
public enum Alert implements org.apache.avro.generic.GenericEnumSymbol<Alert> {
  CRITICAL, MINOR  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"Alert\",\"namespace\":\"Rule\",\"symbols\":[\"CRITICAL\",\"MINOR\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}