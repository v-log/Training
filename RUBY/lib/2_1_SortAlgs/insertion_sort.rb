def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def insertion_sort(a)
  n = a.size
  (1...n).each do |i|
    j = i
    while j > 0 && a[j] <= a[j - 1]
      a[j], a[j - 1] = a[j - 1], a[j]
      j -= 1
    end
  end
  a
end

def insertion_sort2(a)
  n = a.size
  get_var = block_given? ? lambda { |item| yield(item) } : lambda { |item| item }
  (1...n).each do |i|
    j = i
    while j > 0 && get_var[a[j]] <= get_var[a[j - 1]]
      a[j], a[j - 1] = a[j - 1], a[j]
      j -= 1
    end
  end
  a
end

a = [5,3,2,1,4]
puts sorted?(a)
insertion_sort(a)
raise unless sorted?(a)
puts a.inspect

b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
insertion_sort2(b) { |item| -item[1] }
puts b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]

rand_hash_1 = {}
100.times { |n| rand_hash_1[n] = rand(0..99) }
rand_hash_2 = rand_hash_1.clone
insertion_sort2(rand_hash_1) { |item| -item[1] }
# puts rand_hash_1.inspect
puts
# puts rand_hash_2.inspect
