# Effective Java

[TOC]

## 静态工厂模式 *Static Factory*：避免创建对象

### 情景描述
创建新对象时开销较大

不使用类提供的 *Constructor*，而是采用 ```static``` 方法对外提供一个 *Instance*

### 优点

* 使用 `class` 提供的有**名称**的方法进行 *Instance* 声明（代码可读性更强）
* 不需要创建新对象，使用预先构建好的 *Instance*（对经常请求创建相同对象、对象创建代价高有用）
* 可以返回子类对象
* 返回对象的 `class` 可以随着不同的参数值而发生变化（允许返回子类）
* 返回对象的 `class` 可以在编写该静态工厂时不存在

### 缺点

* 如果不含 `public`/`protect` 修饰的 *Constructor*，无法被 `expends`
* 与其他 `static` 方法没有区别，导致 Javadoc 工具无法发现

### 代码示例

#### String 的实例化
在 Java 中，需要尽量避免创造多余的 `String` 实例来提升性能

以正则表达式的判断为例(`String.matches(String pattern)`)，每次的正则表达式判断几乎都是一个实例化一次就不再使用的对象。为了提升性能，可以将正则表达式编译成一个 [`Pattern`](https://www.runoob.com/java/java-regular-expressions.html) 实例

下面的例子是字符串的正则匹配避免实例化正则表达式的写法：

```java
// 实例化了一个 Pattern 的正则表达式匹配
static boolean isEmailAddress(String s) {
	return s.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$");
}

// 将 Pattern 作为静态对象，只提供给 isEmailAddress 方法使用
public class EmailAddress {
	
	// 使用了 EMAIL_ADDRESS 后，代码可读性比直接使用正则表达式更高
	private static final Pattern EMAIL_ADDRESS = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$");
	
	static boolean isEmailAddress(String s) {
		return EMAIL_ADDRESS.matcher(s).matches();
	}
}
```

在多次调用 `isEmailAddress` 方法时，第二种实现方式的速度更快

#### 自动装箱 *autoboxing*

基本类型与装箱基本类型混用时，如果发生了自动装箱，会产生**使用基本类型实例化一个装箱基本类型实例**的问题，而过多的实例化过程会造成代码效率降低

```java
// sum 是装箱基本类型
Long sum = 0L;
// i 是基本类型
for(long i = 0; i < Integer.MAX_VALUE; i ++){
	sum += i; // 此处发生自动装箱
}
```

### 常用命名

```java
// from, 类型转换
Date date = Date.from(instant);

// of, 聚合枚举
Set<Rank> cards = EnumSet.of(JACK, QUEEN, KING);

// valueOf, 静态类型转换
BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);

// instance/getInstance, 参数获取实例
StackWalker luke = StackWaler.getInstance(options);

// newInstance/create, 获取新实例
Object newArray = Array.newInstance(classObject, arrayLen);

// getType, 获取工厂方法返回的对象类型，用于不同类
FileStore fileStore = Files.getFileStore(path);

// newType, 获取新实例，用于不同类
BufferedReader bufferedReader = Files.newBufferedReader(path);

// getType 与 newType 的简化版
List<Complaint> litany = Collection.list(legacyLitany);
```
## 构造者模式 *Builder*：多参数构造器

### 情景描述
必要构造参数与可选构造参数同时存在多个，造成必要构造参数与若干个可选参数的 *Constructor*；多参数 *Constructor* ，相同类型参数的顺序依赖性较强；JavaBeans模式（无参数 *Constructor* 创建对象后使用 *setter* 方法赋值）会导致对象内数据被更改

使用全部必要参数调用 *Builder* 得到 `builder` 对象，在此 `builder` 上调用不同的 `setter` 为可选参数赋值，使用参数为该 `builder` 的方法获取一个静态目标对象

### 优点
* 使用具体名的方法为可选参数赋值，可读性更高
* 创建出的对象内数据不可更改，更安全

### 缺点
* 创建对象前不得不先创建 `builder`
* 参数较少时优势不明显

### 代码示例
```java
// 类内静态成员方式
public class MyClass {
	// 对象内不可变的成员变量
	private final int requiredData;
	private final int optionalData;
	
	// 静态 Builder 部分
	public static class Builder {
		
		// 必须声明的参数
		private final int requiredDate;
		
		// 可选的参数
		private int optionalData;
		
		// builder 的构造函数
		public Builder (int requiredData) {
			this.requiredData = requiredData;
		}
		
		// 可选参数的赋值函数
		public Builder (int optionalData) {
			this.optionalData = optionalData;
			return this;
		}
		
		// 返回 builder 创建的对象
		public MyClass build() {
			return new MyClass(this);
		}
	}
	
	// 不对外暴露的目标对象构造函数
	private MyClass(Builder builder) {
		this.requiredData = builder.requiredData;
		this.optionalData = builder.optionalData;
	}
}

// 类层次结构（抽象类使用抽象 builder，具体累类用具体 builder）
public abstract class FatherClass {
	final int requiredData;
	
	abstract static class Builder<T extends Builder<T>> { // 递归类型参数，recursive type parameter
		int requiredData;
		
		// 参数赋值
		public T requiredData(int requiredData ) {
			this.requiredData = requiredData;
			return self();
		}
		
		// 返回 builder 创建的对象
		// 具体实现：return new SonClass(this);
		abstract FatherClass build();
		
		// 子类必须 override 此方法来返回子类的 this
		// 具体实现：return this;
		protected abstract T self();
	}
	
	// 子类添加 
	// private SonClass(Builder builder) {
	//         super(builder);
	//         sonClassData = builder.sonClassData;
	// }
	
	FatherClass(Builder<?> builder) {
		this.requiredData = builder.requiredData;
	}
}
```
## 单例模式 *Singleton*：仅会被构造一次的对象

### 情景描述

对系统内本质上唯一的组件，其构造函数仅仅可以使用一次。在实例化了这个对象后，不能再实例其他此类对象。

1. 使用 `final` 作用下的实例，将 *Constructor* 放置在 `private` 内（为了避免反射机制修改，可以使用 `try` ，当构造了新的 `Instance` 后抛出异常）
		
	```java
	public class SingletonClass {
		
		// final 作用下的实例
		public static final SingletonClass INSTANCE = new SingletonClass();
		
		// private 作用下的构造函数
		private  SingletonClass() {...}
		
		// builder 模式修改对象内成员变量
		public void builder() {...}
	}
	```

2. 使用静态工厂模式 *Static Factory*，实例仍然采用 `final` 作用

	```java
	public class SingletonClass {
		
		// final 作用下的实例
		private static final SingletonClass INSTANCE = new SingletonClass();
		
		// private 作用下的构造函数
		private  SingletonClass() {...}
		
		// 返回该实例的静态对象
		public static SingletonClass getInstance() {
			return INSTANCE;
		}
		
		// builder 模式修改对象内成员变量
		public void builder() {...}
	}
	```
	
3. 使用单元素的 `enum`

	```java
	public enum SingletonClass {
		
		// 唯一的实例
		INSTANCE;
		
		// builder 模式修改对象内成员变量
		public void builder() {...}
	}
	```
	
### 优点
1. 代码明显，实现简单
2. 不改变 API 的情况下可以决定该 *Class* 是否为单例；可以使用范型单例工厂
3. 代码简洁；自带序列化 *Serialization* 机制；防止反射多次实例化

### 缺点
1. 序列化不仅要 `implements Serializable`，还需要声明实例内的成员变量是瞬时的 [*transient*](https://blog.csdn.net/u012723673/article/details/80699029) (序列化过程会被忽略的字段)，并提供 `readResolve` 方法（反序列化过程将成员变量的值赋给唯一的单例）
	
	```java
	private Object readResolve() {
		return INSTANCE;
	}
	```
	
2. 同上
3. 扩展超类（父类）时不宜使用

## 拒绝实例化的 *Util*：私有 *Constructor*

### 情景描述

对于一些 *Util* 类，使用过程中不希望被实例化。然而即使不声明显式的 *Constructor*，编译器会提供一个 `public` 的无参 *default constructor*

即使使用 `abstract`（抽象类），不可避免该类被 `extends` 从而依旧被提供  *default constructor*

对于上述情况，原因是当 `class` 中不包含显式的 *Constructor* 时，编译器会自动生成 *default constructor*。因此可以考虑 `class` 中给出一个 `private` 修饰的 *Constructor*

### 优点

减少不必要的实例化过程

### 缺点
这样的 `class` 无法被 `extends`

原因：子类的 *Constructor* 必须调用到超类（父类）的 *Constructor*（显式的 `super()` 或隐式声明）

### 代码示例

```java
public class UtilClass {
	
	// 私有化构造函数防止实例化
	private UtilClass() {
		throw new AssertionError();
	}
}
```

## 依赖注入 *Dependency Injection*：引入外部资源

### 情景描述

一个类的成员变量中包含了外部资源的类（这个被包含的类即为依赖 *dependency*），在该类 *Constructor* 的参数中需要声明 *dependency*

声明 *dependency* 的方法可以是直接传入一个被依赖的对象；也可以传入该依赖资源的工厂 *factory*（工厂是指被重复调用，来创建所需对象实例的对象；Java8 中可以使用 `Supplier<T>` 接口实现工厂）

实现方式有以下三种：

* *Constructor* 注入
* *Setter* 注入
* 接口注入（基本被废弃）

### 优点
提升灵活性与可测试性

### 缺点
大型项目过多使用依赖注入会造成项目凌乱，然而有 Spring 等依赖注入框架来解决这个问题

### 代码示例

```java
// constructor 与 setter 注入方式
public class MyClass {
	
	// 将会被注入的依赖声明到类内
	private final DependentClass dependentClass;
	
	// constructor 注入，常用
	public MyClass(DependentClass dependentClass) {
		// 防止传入的依赖为 null，酌情使用
		this.dependentClass = Objects.requireNonNull(dependentClass );
	}
	
	// setter 注入，常用
	public void setDependentClass(DependentClass dependentClass ) {
		this.dependentClass = dependentClass;
	}
}

// 接口注入方式
public class MyClass {
	
	// 在使用到被依赖的对象之前不声明依赖
	
	// 接口注入
	public void methodUsingDependentClass(DependentClass dependentClass ) {
		doSomthingWithDependentClass(dependentClass);
	}
}
```

## 消除过期对象引用：避免内存泄漏 *Memory Leak*

### 情景描述

* 在 `ArrayList` 等存储结构，被 `add()` 进 `list` 并使用完后的对象，如果不对此对象执行 `list[index] = null`，长此以往 `list` 中的对象对充满内存
* 使用缓存的过程中，对象可能会长期留存在缓存中
* 