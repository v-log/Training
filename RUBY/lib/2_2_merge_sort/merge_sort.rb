module MergeSort
  def sorted?(a)
    (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
  end

  def merge_sort!(a, &block)
    return a if a.size <= 1
    aux = Array.new(a.size)

    if block_given?
      merge_sort_hlpr!(a, 0, a.size - 1, aux, &block)
    else
      merge_sort_hlpr!(a, 0, a.size - 1, aux, &Proc.new { |x| x })
    end
    a
  end

  def merge_sort_bu!(a, &block)
    return a if a.size <= 1
    n = a.length
    aux = Array.new(n)
    sz = 1

    while sz < n do
      0.step(n - sz, 2 * sz) do |lo|
        hi = less(lo + sz + sz - 1, n - 1)
        if block_given?
          merge!(a, lo, lo + sz - 1, hi, aux, &block)
        else
          merge!(a, lo, lo + sz - 1, hi, aux) { |x| x }
        end
      end      
      sz += sz
    end
    a
  end

  private

  def less(a, b)
    a < b ? a : b
  end

  def merge_sort_hlpr!(a, lo, hi, aux, &block)
    if hi <= lo
      return a
    end

    mid = lo + (hi - lo)/2
    merge_sort_hlpr!(a, lo, mid, aux, &block)
    merge_sort_hlpr!(a, mid + 1, hi, aux, &block)
    merge!(a, lo, mid, hi, aux, &block)
  end

  def merge! (a, lo, mid, hi, aux, &block)
    i = lo
    j = mid + 1
    (lo..hi).each do |x|
      aux[x] = a[x]
    end

    (lo..hi).each do |x|
      if i > mid
        a[x] = aux[j]
        j += 1
      elsif j > hi
        a[x] = aux[i]
        i += 1
      elsif block.call(aux[j]) < block.call(aux[i])
        a[x] = aux[j]
        j += 1
      else
        a[x] = aux[i]
        i += 1
      end
    end
  end
end

include MergeSort

a = (0..30).to_a.shuffle
puts a.inspect
puts sorted?(a)
#merge_sort!(a)
merge_sort_bu!(a)
raise unless sorted?(a)
puts "result:" + "\n" + a.inspect
puts sorted?(a)
puts 
b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
puts b.inspect
#merge_sort!(b) { |item| -item[1] }
merge_sort_bu!(b) { |item| -item[1] }
puts "result:" + "\n" + b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
